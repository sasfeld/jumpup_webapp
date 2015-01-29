package de.htw.fb4.imi.jumpup.booking.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.htw.fb4.imi.jumpup.booking.BookingEJB;
import de.htw.fb4.imi.jumpup.booking.entities.Booking;
import de.htw.fb4.imi.jumpup.controllers.AbstractFacesController;
import de.htw.fb4.imi.jumpup.settings.BeanNames;
import de.htw.fb4.imi.jumpup.user.controllers.Login;

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
    private Login loginController;

    @Inject
    private BookingEJB bookingEJB;

    private long tripId;

    private Booking booking = new Booking();

    public String bindBookingData()
    {

        booking.setPassenger(loginController.getLoginModel().getCurrentUser());

        bookingEJB.getTripByID(tripId);

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
        this.tripId = tripId;
    }

}
