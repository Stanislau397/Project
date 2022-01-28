package edu.epam.project.entity;

/**
 * Class represents movieCountry
 *
 * @author Stanislau Kachan
 */
public class Country extends Entity {

    private long countryId;
    private String countryName;

    private Country() {

    }

    public long getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public static CountryBuilder newCountryBuilder() {
        return new Country().new CountryBuilder();
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

    public class CountryBuilder {

        private CountryBuilder() {

        }

        public CountryBuilder withId(long countryId) {
            Country.this.countryId = countryId;
            return this;
        }

        public CountryBuilder withCountryName(String countryName) {
            Country.this.countryName = countryName;
            return this;
        }

        public Country build() {
            return Country.this;
        }
    }
}
