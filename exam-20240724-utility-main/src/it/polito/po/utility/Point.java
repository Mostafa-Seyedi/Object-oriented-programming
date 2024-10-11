package it.polito.po.utility;

import java.util.Optional;

/**
 * Represents a point in 2D coordinate system
 * 
 */
public class Point implements ServicePoint{
    private String id;
    private String municipality;
    private String address; 
    private Point position; 

    public void setPosition(Point position) {
        this.position = position;
    }

    private Optional<Met> meter = Optional.empty();
     public void setMeter(Optional<Met> meter) {
        this.meter = meter;
    }

    /**
         * The longitude of this {@code Point}.
         */
        public final double lon;

        /**
         * The latitude of this {@code Point}.
         */
        public final double lat;

        public Point(String id, String municipality, String address, double lat, double lon) {
            this.id = id;
            this.municipality = municipality;
            this.address = address;
            this.lat = lat;
            this.lon = lon;
        }

        /**
         * Constructs and initializes a {@code Point} with the
         * specified coordinates.
         *
         * @param lon the longitude of the newly
         *          constructed {@code Point}
         * @param lat the latitude of the newly
         *          constructed {@code Point}
         */
        public Point(double lon, double lat) {
            this.lon = lon;
            this.lat = lat;
        }

        /**
         * retrieves the longitude
         * @return the longitude
         */
        public double getLon() {
            return lon;
        }

        /**
         * Retrieves the latitude
         * @return the latitude
         */
        public double getLat() {
            return lat;
        }

        /**
         * Returns a {@code String} that represents the value
         * of this {@code Point}.
         * @return a string representation of this {@code Point}.
         */
        public String toString() {
            return "Point("+lon+", "+lat+")";
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getMunicipality() {
           return municipality;
        }

        @Override
        public String getAddress() {
            return address;
        }

        @Override
        public Point getPosition() {
            return position;
        }

        @Override
        public Optional<Meter> getMeter() {
            return meter.map(m->(Meter) m);
        }

}
