package rw.ikimina.ikimina_manager.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikimina.ikimina_manager.domain.LoanStatus;
import rw.ikimina.ikimina_manager.repository.LoanRepository;
import rw.ikimina.ikimina_manager.repository.MemberRepository;
import rw.ikimina.ikimina_manager.repository.RepaymentRepository;
import rw.ikimina.ikimina_manager.repository.SavingsGroupRepository;
import rw.ikimina.ikimina_manager.repository.TreasurerRepository;

@Service
public class DashboardService {

    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private TreasurerRepository treasurerRepo;

    @Autowired
    private SavingsGroupRepository savingsGroupRepo;

    @Autowired
    private LoanRepository loanRepo;

    @Autowired
    private RepaymentRepository repaymentRepo;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("members", memberRepo.count());
        summary.put("treasurers", treasurerRepo.count());
        summary.put("freeSavingsGroups", (long) savingsGroupRepo.findFreeGroups().size());
        summary.put("loansToday", loanRepo.countByLoanDate(LocalDate.now()));
        summary.put("requested", loanRepo.countByStatus(LoanStatus.REQUESTED));
        summary.put("repayments", repaymentRepo.count());
        return summary;
    }
}
