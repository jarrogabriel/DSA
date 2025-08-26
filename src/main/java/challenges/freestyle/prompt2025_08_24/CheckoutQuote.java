package challenges.freestyle.prompt2025_08_24;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

// INPUT DTO
/* {
  "merchantCurrency": "USD",
  "userCurrency": "BRL",
  "country": "BR",
  "locale": "pt_BR",
  "paymentMethod": "VISA",
  "coupon": { "type": "PERCENT", "value": 10 },
  "items": [
    { "sku": "AA-1", "category": "general", "unitPrice": 12.30, "quantity": 2 },
    { "sku": "BB-2", "category": "food",    "unitPrice": 3.99,  "quantity": 5 }
  ]
}

*/


// OUTPUT DTO

/*
{
  "currency": "BRL",
  "lines": [
    { "sku": "AA-1", "category": "general", "subtotal": 123.00, "discountApplied": 12.30, "tax": 22.14 },
    { "sku": "BB-2", "category": "food",    "subtotal": 99.75,  "discountApplied": 9.98,  "tax": 10.77 }
  ],
  "subtotal": 222.75,
  "discountTotal": 22.28,
  "taxTotal": 32.91,
  "paymentFee": 3.50,
  "grandTotal": 236.89,
  "assumptions": [
    "Discount applied per line before tax.",
    "Rounding HALF_UP to 2 decimals for BRL.",
    "Payment fee applied after tax."
  ],
  "warnings": [],
  "trace": {
    "fxAttempts": 2,
    "taxAttempts": 1,
    "feeAttempts": 1,
    "fallbacksUsed": []
  }
}
*/
// RULES
// Convert each item unitPrice from merchantCurrency → userCurrency using FX rate.
// Compute line subtotal = priceInUserCurrency * quantity.
// Apply an optional coupon.
// Apply tax per line (country & category).
// Apply payment processing fee as a percentage of the final amount (see ambiguity).
// Rounding:
// If userCurrency is "JPY", use 0 decimals.
// Otherwise default to 2 decimals, HALF_UP (unless you design a locale-aware rule).

/* Your job is to build a small “controller” that:

Parses input JSON, validates fields.

Calls these services with retry / timeout / fallback logic.

Applies your business rules.

Emits the response JSON exactly once to STDOUT. */

public class CheckoutQuote {


    record InputJsonDTO(String merchantCurrency, // "USD"
                        String userCurrency, // "BRL"
                        String country, // "BR"
                        String locale, // "pt_BR",
                        String paymentMethod, // "VISA"
                        CouponDTO coupon, List<ItemDTO> items) {
        record CouponDTO(String type, Integer value) {
        }

        record ItemDTO(String sku, //AA-1
                       String category, //"general"
                       BigDecimal unitPrice, // 12.30
                       Integer quantity // 2
        ) {
        }
    }

    record OutputDTO(String currency, List<LineDTO> lines, BigDecimal subTotal, BigDecimal discountTotal,
                     BigDecimal taxTotal, BigDecimal paymentFee, BigDecimal grandTotal) {
        record LineDTO(String sku, String category, BigDecimal subTotal, BigDecimal discountApplied, BigDecimal tax) {
        }
    }

    // Simulates Fx API
    static class FxService {
        // Transient failure on first USD->BRL call; succeed on retry.
        //private static boolean firstFxFail = true;

        static double getRate(String from, String to) throws java.io.IOException {
            if (from.equals("USD") && to.equals("BRL")) {
                //if (firstFxFail) { firstFxFail = false; throw new java.io.IOException("429 Too Many Requests"); }
                return 5.00; // deterministic after retry
            }
            if (from.equals("BRL") && to.equals("USD")) return 0.20;
            if (from.equals("USD") && to.equals("JPY")) return 150.00;
            if (from.equals("JPY") && to.equals("USD")) return 0.0066666667;
            throw new java.io.IOException("Unsupported pair");
        }
    }

    // Simulates Tax API (may time out)
    static class TaxService {
        static Double getTaxRate(String country, String category) /* throws java.io.IOException*/ {
            // Simulated slow path that should hit your timeout if you pick a small one:
    /* if (country.equals("US") && category.equals("general")) {
      try { Thread.sleep(300); } catch (InterruptedException ignored) {}
      return 0.10;
    } */
            if (country.equals("US") && category.equals("general")) return 0.10;
            if (country.equals("BR") && category.equals("food")) return 0.12;
            if (country.equals("BR") && category.equals("general")) return 0.20;
            if (country.equals("US") && category.equals("food")) return 0.07;
            //throw new java.io.IOException("Unknown country/category");
            return null;
        }
    }


    // Simulates Payment Config API
    static class PaymentConfigService {
        static Double getFeePercent(String method) /* throws java.io.IOException */ {
            if ("VISA".equals(method)) return 0.015;       // 1.5%
            if ("MASTERCARD".equals(method)) return 0.0175;
            if ("PIX".equals(method)) return 0.0;
            return null; // unknown -> ambiguity: treat as 0% or warn?
        }
    }


    record Helper(String currency) {

        BigDecimal convert(BigDecimal unitPrice, double rate) {
            BigDecimal ratBigDecimal = BigDecimal.valueOf(rate);
            return round(unitPrice.multiply(ratBigDecimal), this.currency);
        }

        BigDecimal calcLineSubtotal(BigDecimal itemPrice, Integer itemQuantity) {
            return round(itemPrice.multiply(BigDecimal.valueOf(itemQuantity)), this.currency);
        }

        BigDecimal calcLineDiscount(BigDecimal subTotal, InputJsonDTO.CouponDTO couponDTO) {
            BigDecimal discountValue = new BigDecimal("0.00");
            //System.out.println(couponDTO);
            if (("PERCENT").equals(couponDTO.type)) {
                discountValue = BigDecimal.valueOf(couponDTO.value).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
                //System.out.println(discountValue);
            }
            return round(subTotal.multiply(discountValue), this.currency);
        }

        BigDecimal calcTaxes(BigDecimal taxableValue, Double tax) {

            return round(taxableValue.multiply(BigDecimal.valueOf(tax)), this.currency);
        }

        BigDecimal round(BigDecimal value, String currency) {
            int scale = currency.equals("JPY") ? 0 : 2;
            return value.setScale(scale, RoundingMode.HALF_UP);
        }
    }

    public static void main(String[] args) throws Exception {
        InputJsonDTO inputJsonDTO = new Gson().fromJson(
                new java.io.FileReader("src/main/java/challenges/freestyle/prompt2025_08_24/Input.json"),
                InputJsonDTO.class
        );
        String userCurrency = inputJsonDTO.userCurrency;
        //System.out.println(inputJsonDTO.toString());
        double rate = CheckoutQuote.FxService.getRate(inputJsonDTO.merchantCurrency, userCurrency);


        String country = inputJsonDTO.country;
        Helper helper = new Helper(userCurrency);
        List<OutputDTO.LineDTO> lineList = new ArrayList<>();
        BigDecimal subTotal = new BigDecimal("0.00");
        BigDecimal discountTotal = new BigDecimal("0.00");
        BigDecimal taxTotal = new BigDecimal("0.00");
        for (InputJsonDTO.ItemDTO item : inputJsonDTO.items) {
            BigDecimal itemPrice = helper.convert(item.unitPrice, rate);

            BigDecimal loopSubTotal = helper.calcLineSubtotal(itemPrice, item.quantity);

            BigDecimal loopDiscountApplied = helper.calcLineDiscount(loopSubTotal, inputJsonDTO.coupon);

            BigDecimal taxableValue = loopSubTotal.subtract(loopDiscountApplied);

            Double taxRate = CheckoutQuote.TaxService.getTaxRate(country, item.category);
            BigDecimal tax = helper.calcTaxes(taxableValue, taxRate);

            OutputDTO.LineDTO lineDto = new OutputDTO.LineDTO(item.sku, item.category, loopSubTotal, loopDiscountApplied, tax);
            lineList.add(lineDto);
            subTotal = subTotal.add(loopSubTotal);
            discountTotal = discountTotal.add(loopDiscountApplied);
            taxTotal = taxTotal.add(tax);
        }

        BigDecimal grandTotalBeforeFee = subTotal.subtract(discountTotal).add(taxTotal);

        Double feePercent = CheckoutQuote.PaymentConfigService.getFeePercent(inputJsonDTO.paymentMethod);
        if (feePercent == null) feePercent = 0.0;

        BigDecimal paymentFee = helper.round(grandTotalBeforeFee.multiply(BigDecimal.valueOf(feePercent)), userCurrency);
        BigDecimal grandTotal = helper.round(grandTotalBeforeFee.add(paymentFee), userCurrency);

        OutputDTO outputDTO = new OutputDTO(userCurrency, lineList, subTotal, discountTotal, taxTotal, paymentFee, grandTotal);

        // TODO: validate fields; build assumptions list; pick rules for:
        // - discount location
        // - rounding strategy
        // - fee placement
        // - fallbacks on timeouts/unknowns

        // TODO: fetch FX with retries; tax with timeout; fee with single call
        // TODO: compute lines, totals, and fill Response

        System.out.print(new GsonBuilder().setPrettyPrinting().create().toJson(outputDTO));
    }


}
