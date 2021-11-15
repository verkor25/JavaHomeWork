package com.pb.lozumirskij.hw7;

public abstract class Clothes {
    private final Size size;
    private final int cost;
    private final String color;

    public Clothes(Size size, int cost, String color) {
        this.size = size;
        this.cost = cost;
        this.color = color;
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + " {" +
                "size=" + size +
                ", cost=" + cost +
                ", color='" + color + '}';
    }

    public enum Size {
        XXS ("child size", 32),
        XS ("adult size", 34),
        S ("adult size", 35),
        M ("adult size", 38),
        L ("adult size", 40);

        String description;
        int euroSize;

        Size(String description, int euroSize) {
            this.description = description;
            this.euroSize = euroSize;
        }

        public String getDescription() {
            return description;
        }

        public int getEuroSize() {
            return euroSize;
        }

        @Override
        public String toString() {
            return "Size " +  super.toString() + " {" +
                    "description='" + description + ", euroSize=" + euroSize + "} ";
        }
    }
}




