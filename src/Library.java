import java.util.ArrayList;

public class Library {
    private ArrayList<Member> members;
    private ArrayList<Resource> resources;
    private ArrayList<Loan> loans;

    public Library() {
        members = new ArrayList<>();
        resources = new ArrayList<>();
        loans = new ArrayList<>();
    }

    public boolean addMember(Member member) {
        for (Member existingMember : members) {
            if (existingMember.getMemberId() == member.getMemberId()) {
                return false;
            }
        }

        members.add(member);
        return true;
    }

    public Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }

        return null;
    }

    public boolean addResource(Resource resource) {
        for (Resource existingResource : resources) {
            if (existingResource.getIdentifier().equals(resource.getIdentifier())) {
                return false;
            }
        }

        resources.add(resource);
        return true;
    }

    public Resource findResource(String identifier) {
        for (Resource resource : resources) {
            if (resource.getIdentifier().equals(identifier)) {
                return resource;
            }
        }

        return null;
    }

    public ArrayList<Resource> searchResources(String title) {
        ArrayList<Resource> results = new ArrayList<>();

        for (Resource resource : resources) {
            if (resource.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(resource);
            }
        }

        return results;
    }

    public LoanResult loan(int memberId, String identifier) {
        Member member = findMember(memberId);

        if (member == null) {
            return LoanResult.MEMBER_NOT_FOUND;
        }

        Resource resource = findResource(identifier);

        if (resource == null) {
            return LoanResult.RESOURCE_NOT_FOUND;
        }

        for (Loan loan : loans) {
            if (loan.getResource().getIdentifier().equals(identifier)) {
                return LoanResult.ALREADY_LOANED;
            }
        }

        loans.add(new Loan(member, resource));
        return LoanResult.SUCCESS;
    }

    public boolean returnLoan(String identifier) {
        for (Loan loan : loans) {
            if (loan.getResource().getIdentifier().equals(identifier)) {
                loans.remove(loan);
                return true;
            }
        }

        return false;
    }

    public ArrayList<Loan> getMemberLoans(int memberId) {
        ArrayList<Loan> results = new ArrayList<>();

        for (Loan loan : loans) {
            if (loan.getMember().getMemberId() == memberId) {
                results.add(loan);
            }
        }

        return results;
    }


    public ArrayList<Loan> getOverdueLoans() {
        ArrayList<Loan> results = new ArrayList<>();

        for (Loan loan : loans) {
            if (loan.getDueDate().isBefore(java.time.LocalDate.now())) {
                results.add(loan);
            }
        }

        return results;
    }
}
