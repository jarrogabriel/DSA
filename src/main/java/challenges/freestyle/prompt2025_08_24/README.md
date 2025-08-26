# Localized Checkout Quote API

## Problem Statement
Build a small program in **Java** that reads a JSON request (STDIN) and outputs a JSON response (STDOUT).  
It must calculate a localized checkout quote by calling **downstream services** (mocked):

- **FxService** → currency conversion (merchant → user currency)
- **TaxService** → tax rate per country/category (may timeout)
- **PaymentConfigService** → payment method fee percentage

Only **Gson** is available. No frameworks.  
Time limit: ~45 minutes.

---

## Input Example
```json
{
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
```
## Output Example
```json
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
    "Discount applied per line before tax",
    "Rounding HALF_UP to 2 decimals for BRL",
    "Payment fee applied after tax"
  ],
  "warnings": [],
  "trace": {
    "fxAttempts": 2,
    "taxAttempts": 1,
    "feeAttempts": 1,
    "fallbacksUsed": []
  }
}
```
