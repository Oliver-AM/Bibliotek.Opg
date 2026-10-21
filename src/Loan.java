import java.time.LocalDate;

public class Loan {
    private Member member;
    private Resource resource;
    private LocalDate loanDate;
    private LocalDate dueDate;

    public Loan(Member member, Resource resource) {
        this.member = member;
        this.resource = resource;
        this.loanDate = LocalDate.now();
        this.dueDate = loanDate.plusDays(30);
    }

    public Loan(Member member, Resource resource, LocalDate loanDate) {
        this.member = member;
        this.resource = resource;
        this.loanDate = loanDate;
        this.dueDate = loanDate.plusDays(30);
    }

    public Member getMember() {
        return member;
    }

    public Resource getResource() {
        return resource;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
