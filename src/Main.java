import java.util.ArrayList;

void main() {
    Library library = new Library();
    boolean running = true;

    // Testmaterialer
    library.addResource(new Book("9783161484100", "Harry Potter", "J.K. Rowling", 350));
    library.addResource(new Movie("5701234567890", "Toy Story", "Pixar", "John Lasseter", 81));
    library.addResource(new AudioBook("9781234567897", "The Hobbit", "J.R.R. Tolkien", 680));
    library.addResource(new VideoGame("5709876543210", "Minecraft", "Mojang", "PC"));

    while (running) {
        IO.println("\n=== BIBLIOTEKSSYSTEM ===");
        IO.println("1. Registrer ny låner");
        IO.println("2. Find låner");
        IO.println("3. Søg efter materialer");
        IO.println("4. Lån materiale");
        IO.println("5. Aflever materiale");
        IO.println("6. Se en låners aktive lån");
        IO.println("7. Se overskredne lån");
        IO.println("8. Afslut");

        String choice = IO.readln("Vælg en mulighed: ");

        switch (choice) {
            case "1" -> {
                String name = IO.readln("Indtast navn: ");
                try {
                    int memberId = Integer.parseInt(IO.readln("Indtast medlemsnummer: "));
                    Member member = new Member(name, memberId);

                    if (library.addMember(member)) {
                        IO.println("Låneren er blevet registreret.");
                    } else {
                        IO.println("Der findes allerede en låner med dette medlemsnummer.");
                    }
                } catch (NumberFormatException e) {
                    IO.println("Medlemsnummer skal være et helt tal.");
                }
            }

            case "2" -> {
                try {
                    int memberId = Integer.parseInt(IO.readln("Indtast medlemsnummer: "));
                    Member member = library.findMember(memberId);

                    if (member != null) {
                        IO.println("Låner fundet: " + member.getName());
                    } else {
                        IO.println("Ingen låner fundet med dette medlemsnummer.");
                    }
                } catch (NumberFormatException e) {
                    IO.println("Medlemsnummer skal være et helt tal.");
                }
            }

            case "3" -> {
                String title = IO.readln("Søg efter titel: ");
                ArrayList<Resource> results = library.searchResources(title);

                if (results.isEmpty()) {
                    IO.println("Ingen materialer fundet.");
                } else {
                    for (Resource resource : results) {
                        IO.println(resource.getTitle() + " - " +
                                resource.getCreator() + " (ID: " +
                                resource.getIdentifier() + ")");
                    }
                }
            }

            case "4" -> {
                try {
                    int memberId = Integer.parseInt(IO.readln("Indtast medlemsnummer: "));
                    String identifier = IO.readln("Indtast ISBN eller EAN: ");

                    LoanResult result = library.loan(memberId, identifier);

                    switch (result) {
                        case SUCCESS -> IO.println("Materialet er blevet udlånt.");
                        case MEMBER_NOT_FOUND -> IO.println("Låneren findes ikke i biblioteket.");
                        case RESOURCE_NOT_FOUND -> IO.println("Materialet findes ikke i biblioteket.");
                        case ALREADY_LOANED -> IO.println("Materialet er allerede udlånt.");
                    }
                } catch (NumberFormatException e) {
                    IO.println("Medlemsnummer skal være et helt tal.");
                }
            }

            case "5" -> {
                String identifier = IO.readln("Indtast ISBN eller EAN: ");

                if (library.returnLoan(identifier)) {
                    IO.println("Materialet er afleveret og er nu ledigt.");
                } else {
                    IO.println("Materialet har ikke et aktivt lån.");
                }
            }

            case "6" -> {
                try {
                    int memberId = Integer.parseInt(IO.readln("Indtast medlemsnummer: "));
                    Member member = library.findMember(memberId);

                    if (member == null) {
                        IO.println("Låneren findes ikke.");
                    } else {
                        ArrayList<Loan> loans = library.getMemberLoans(memberId);

                        if (loans.isEmpty()) {
                            IO.println("Låneren har ingen aktive lån.");
                        } else {
                            for (Loan loan : loans) {
                                IO.println(loan.getResource().getTitle() +
                                        " - Afleveringsfrist: " + loan.getDueDate());
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    IO.println("Medlemsnummer skal være et helt tal.");
                }
            }

            case "7" -> {
                ArrayList<Loan> overdueLoans = library.getOverdueLoans();

                if (overdueLoans.isEmpty()) {
                    IO.println("Ingen overskredne lån.");
                } else {
                    for (Loan loan : overdueLoans) {
                        IO.println(loan.getResource().getTitle() +
                                " - Låner: " + loan.getMember().getName() +
                                " - Frist: " + loan.getDueDate());
                    }
                }
            }

            case "8" -> {
                IO.println("Programmet afsluttes.");
                running = false;
            }

            default -> IO.println("Ugyldigt valg. Prøv igen.");
        }
    }
}
