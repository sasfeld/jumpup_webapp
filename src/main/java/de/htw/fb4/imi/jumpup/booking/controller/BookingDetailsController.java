package de.htw.fb4.imi.jumpup.booking.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.htw.fb4.imi.jumpup.Application;
import de.htw.fb4.imi.jumpup.Application.LogType;
import de.htw.fb4.imi.jumpup.booking.BookingEJB;
import de.htw.fb4.imi.jumpup.booking.entities.Booking;
import de.htw.fb4.imi.jumpup.controllers.AbstractFacesController;
import de.htw.fb4.imi.jumpup.settings.BeanNames;

/**
 * 
 * <p>
 * Controller classes for bookings.
 * </p>
 * 
 * @author <a href="mailto:m_seidler@hotmail.de">Marco Seidler</a>
 * @since 29.01.2015
 * 
 */

@Named(value = BeanNames.BOOKING_DETAILS_CONTROLLER)
@RequestScoped
public class BookingDetailsController extends AbstractFacesController implements
        Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -4989295237379145060L;

    @Inject
    private BookingEJB bookingEJB;

    private long tripId;

    private Booking booking = new Booking();

    /**
     * Bind-booking-data action.
     * @return
     */
    public String bindBookingData()
    {
        try {
            bookingEJB.createBooking(this.getBooking(), this.getTripId());
            
            if (bookingEJB.hasError()) {
                // show first error message
                this.addDisplayErrorMessage(bookingEJB.getErrors()[0]);
                
                return null;
            }

            // no error, redirect to booking page
            addDisplayInfoMessage("Your booking was successful! You will recieve an eMail with further information and are able to view the details in your booking overview.");
        } catch (Exception e) {
            Application.log(e.getMessage(), LogType.ERROR, getClass());
            addDisplayErrorMessage("Your given values aren't valid.");
        }

        return null;
    }

    public Booking getBooking()
    {
        return booking;
    }

    public void setBooking(Booking booking)
    {
        this.booking = booking;
    }

    public long getTripId()
    {
        return tripId;
    }

    public void setTripId(long tripId)
    {
        Application.log("Setting tripID " + tripId, LogType.DEBUG, getClass());
        this.tripId = tripId;
    }

}
