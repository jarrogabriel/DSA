package challenges.freestyle.prompt2025_08_23;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

public class DasherPayout {

    public static void main(String[] args) {

        DeliveryService deliveryService = new MockDeliveryService();
        DasherPayoutService dasherPayoutService = new DasherPayoutServiceImpl(deliveryService);
        BigDecimal result = dasherPayoutService.payoutAmount(new DasherPayoutRequestDTO("1"));
        System.out.println(result);
    }

    interface DeliveryService {
        List<DeliveryResponseDTO> getDeliveries();
    }


    interface DasherPayoutService {
        BigDecimal payoutAmount(DasherPayoutRequestDTO dasherPayoutRequestDTO);
    }

    record DeliveryResponseDTO(String dasherId, String deliveryId, Instant startedAt, String status) {
    }

    record DasherPayoutRequestDTO(String dasherId) {
    }

    record DasherPayoutServiceImpl(DeliveryService deliveryService) implements DasherPayoutService {

        public BigDecimal payoutAmount(DasherPayoutRequestDTO dasherPayoutRequestDTO) {

            List<DeliveryResponseDTO> deliveries = deliveryService.getDeliveries();
            HashMap<String, Instant> deliverieResponseHashMap = new HashMap<>();
            long totalMinutesForDelivery = 0L;
            for (DeliveryResponseDTO deliveryResponseDTO : deliveries) {

                if (!dasherPayoutRequestDTO.dasherId.equals(deliveryResponseDTO.dasherId)) continue;

                if (("ACCEPTED").equals(deliveryResponseDTO.status)) {
                    deliverieResponseHashMap.put(deliveryResponseDTO.deliveryId, deliveryResponseDTO.startedAt);
                } else if (("DONE").equals(deliveryResponseDTO.status)) {
                    Instant acceptedInstant = deliverieResponseHashMap.get(deliveryResponseDTO.deliveryId);
                    if (acceptedInstant != null) {
                        long minutesForDelivery = ChronoUnit.MINUTES.between(acceptedInstant, deliveryResponseDTO.startedAt);
                        if (minutesForDelivery > 0) totalMinutesForDelivery += minutesForDelivery;
                        deliverieResponseHashMap.remove(deliveryResponseDTO.deliveryId);
                    }
                }
            }
            BigDecimal totalPayout = new BigDecimal("0.30").multiply(BigDecimal.valueOf(totalMinutesForDelivery));

            return totalPayout.setScale(2, RoundingMode.HALF_UP);
        }
    }

    static class MockDeliveryService implements DeliveryService {
        public List<DeliveryResponseDTO> getDeliveries() {
            Instant now = Instant.now();
            return List.of(
                    new DeliveryResponseDTO("1", "1", now, "ACCEPTED"),
                    new DeliveryResponseDTO("1", "1", now.plus(10, ChronoUnit.MINUTES), "DONE"),
                    new DeliveryResponseDTO("1", "2", now.plus(15, ChronoUnit.MINUTES), "ACCEPTED"),
                    new DeliveryResponseDTO("1", "2", now.plus(32, ChronoUnit.MINUTES), "DONE"),
                    new DeliveryResponseDTO("2", "3", now.plus(40, ChronoUnit.MINUTES), "ACCEPTED"));
        }
    }
}
