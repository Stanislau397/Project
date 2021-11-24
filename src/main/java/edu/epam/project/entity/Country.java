package edu.epam.project.entity;

/**
 * Class represents movieCountry
 * @author Stanislau Kachan
 */
public class Country extends Entity {

    private long countryId;
    private String countryName;

    /**
     * Constructor of Country object
     * @param countryId long value of countryId
     * @param countryName String value of countryName
     */
    public Country(long countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * Getter method of countryId
     * @return long value of countryId
     */
    public long getCountryId() {
        return countryId;
    }

    /**
     * Setter method of countryId
     * @param countryId long value of countryId
     */
    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    /**
     * Getter method of countryName
     * @return String object of countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Setter method of countryName
     * @param countryName String object of countryName
     */
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
