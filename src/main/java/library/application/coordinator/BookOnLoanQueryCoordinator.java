package library.application.coordinator;

import library.application.service.BookOnLoanQueryService;
import library.application.service.member.MemberQueryService;
import org.springframework.stereotype.Service;

/**
 * 貸出図書参照コーディネーター
 */
@Service
public class BookOnLoanQueryCoordinator {
    MemberQueryService memberQueryService;
    BookOnLoanQueryService bookOnLoanQueryService;
}
