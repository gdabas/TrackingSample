package com.example.tracking;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/**
 * Created by Dabas on 2018-03-23.
 */

@Root(name = "tracking-summary", strict = false)
public class Summary {

    @Element(name = "expected-delivery-date")
    @Path("pin-summary")
    private String expectedDate;

    @Element(name = "actual-delivery-date")
    @Path("pin-summary")
    private String deliveryDate;

    @Element(name = "event-type")
    @Path("pin-summary")
    private String eventType;

    @Element(name = "event-location")
    @Path("pin-summary")
    private String eventLocation;

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
