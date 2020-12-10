package core.entities;

import core.exceptions.CoreException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws CoreException {
        if (!checkOut.after(checkIn)) {
            throw new CoreException("Check-out date must be after check-in date.");
        }

        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNUmber() {
        return roomNumber;
    }

    public void setRoomNUmber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public Long duration() {
        long diff = this.checkOut.getTime() - this.checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkIn, Date checkOut) throws CoreException {

        Date now = new Date();
        if (checkIn.before(now) || checkOut.before(now)) {
            throw new CoreException("Reservation dates for update must be future date.");
        } else if (!checkOut.after(checkIn)) {
            throw new CoreException("Check-out date must be after check-in date.");
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;

    }

    @Override
    public String toString() {

        return "Room " + this.roomNumber + ", " +
                "checkIn: " + sdf.format(this.checkIn) + ", " +
                "checkOut: " + sdf.format(this.checkOut) + ", " +
                this.duration().toString() + " nights";
    }
}
