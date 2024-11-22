package CO_2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Attendee class to represent an individual attendee.
class Attendee {
    private String id;
    private String name;

    public Attendee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attendee)) return false;
        Attendee attendee = (Attendee) o;
        return id.equals(attendee.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}

// Event class to represent an event.
class Event {
    private String eventId;
    private String eventName;

    public Event(String eventId, String eventName) {
        this.eventId = eventId;
        this.eventName = eventName;
    }

    public String getEventId() {
        return eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return eventId.equals(event.eventId);
    }

    @Override
    public int hashCode() {
        return eventId.hashCode();
    }

    @Override
    public String toString() {
        return eventName + " (Event ID: " + eventId + ")";
    }
}

// AttendanceTracker class to manage events and attendees.
class AttendanceTracker {
    private Map<Event, Set<Attendee>> eventAttendance;

    public AttendanceTracker() {
        eventAttendance = new HashMap<>();
    }

    // Adds a new event to the tracker.
    public void addEvent(Event event) {
        eventAttendance.putIfAbsent(event, new HashSet<>());
    }

    // Adds an attendee to an event.
    public void addAttendee(Event event, Attendee attendee) {
        eventAttendance.computeIfAbsent(event, k -> new HashSet<>()).add(attendee);
    }

    // Removes an attendee from an event.
    public void removeAttendee(Event event, Attendee attendee) {
        Set<Attendee> attendees = eventAttendance.get(event);
        if (attendees != null) {
            attendees.remove(attendee);
        }
    }

    // Checks if an attendee is registered for an event.
    public boolean isAttending(Event event, Attendee attendee) {
        Set<Attendee> attendees = eventAttendance.get(event);
        return attendees != null && attendees.contains(attendee);
    }

    // Lists all attendees for an event.
    public Set<Attendee> listAttendees(Event event) {
        return eventAttendance.getOrDefault(event, new HashSet<>());
    }

    // Prints attendance for all events.
    public void printAllAttendance() {
        for (Map.Entry<Event, Set<Attendee>> entry : eventAttendance.entrySet()) {
            System.out.println("Event: " + entry.getKey());
            System.out.println("Attendees: " + entry.getValue());
        }
    }
}

public class EventAttendanceTrackerApp {
    public static void main(String[] args) {
        // Create some events and attendees
        Event event1 = new Event("E001", "Java Conference");
        Event event2 = new Event("E002", "Tech Expo");

        Attendee attendee1 = new Attendee("A001", "Alice");
        Attendee attendee2 = new Attendee("A002", "Bob");
        Attendee attendee3 = new Attendee("A003", "Charlie");

        // Create the tracker and add events
        AttendanceTracker tracker = new AttendanceTracker();
        tracker.addEvent(event1);
        tracker.addEvent(event2);

        // Add attendees to events
        tracker.addAttendee(event1, attendee1);
        tracker.addAttendee(event1, attendee2);
        tracker.addAttendee(event2, attendee2);
        tracker.addAttendee(event2, attendee3);

        // Check attendance
        System.out.println("Is Alice attending Java Conference? " +
                tracker.isAttending(event1, attendee1));

        // List attendees for Java Conference
        System.out.println("Attendees for Java Conference: " +
                tracker.listAttendees(event1));

        // Print attendance for all events
        tracker.printAllAttendance();
    }
}

