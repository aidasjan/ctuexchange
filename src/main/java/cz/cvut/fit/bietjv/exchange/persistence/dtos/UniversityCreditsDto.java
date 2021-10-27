package cz.cvut.fit.bietjv.exchange.persistence.dtos;

public class UniversityCreditsDto {
    private String name;
    private long credits;

    public UniversityCreditsDto() {
    }

    public UniversityCreditsDto(String name, long credits) {
        this.name = name;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
