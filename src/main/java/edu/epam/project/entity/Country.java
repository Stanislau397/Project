package edu.epam.project.entity;

public class Country extends Entity {

    private long countryId;
    private String countryName;

    public Country(long countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (countryId != country.countryId) return false;
        return countryName != null ? countryName.equals(country.countryName) : country.countryName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (countryId ^ (countryId >>> 32));
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("countryId: ").append(countryId).append("CountryName: ").append(countryName);
        return sb.toString();
    }
}
