package github.ezgikardes.loanapi.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;


@Data
@Builder
public class LoanListForCustomerRequest {
    private Long customerId;
    private Pageable pageable;
}