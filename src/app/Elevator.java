package app;

import java.util.ArrayList;
import java.util.Iterator;

abstract class Elevator {
    int _maxFloors;
    int _maxCapacity;
    int _currentFloor = 0;
    boolean _isGoingUp = true;
    int _distance = 0;
    ArrayList<Person> _PeopleWaiting;
    ArrayList<Person> _PeopleInLift;

    Elevator(int iMaxCapacity, int iMaxFloors, ArrayList<Person> iPeopleWaiting){
        _PeopleWaiting = iPeopleWaiting;
        _PeopleInLift = new ArrayList<Person>(); 
        _maxCapacity =  iMaxCapacity;
        _maxFloors = iMaxFloors;
    }

    public abstract void move();

    boolean isPeopleHaveValidDestination(){
        Iterator<Person> aWaiting = _PeopleWaiting.iterator();
        while (aWaiting.hasNext()) {
          Person aPerson = aWaiting.next();
          if (aPerson.destination > _maxFloors || aPerson.destination < 0 ){
            System.out.println("Person has invalid destination !!" + aPerson.destination);
            return false;
          }
        }
        return true;
    }

    void removePeopleArrivedAtDestination(){
        Iterator<Person> aInLift = _PeopleInLift.iterator();
        while (aInLift.hasNext()) {
            Person aPerson = aInLift.next();
            if (aPerson.destination == _currentFloor){
                aInLift.remove();
            }
        }
    }

    void addPeopleStartingAtThisFloor(){
        Iterator<Person> aWaiting = _PeopleWaiting.iterator();
        while (aWaiting.hasNext()) {
            Person aPerson = aWaiting.next();
            if ((aPerson.start == _currentFloor) &&
                (_PeopleInLift.size() < _maxCapacity))
            {     
                //People only enter the lift if going in the right direction
                if ((aPerson.destination > _currentFloor && _isGoingUp) ||
                   (aPerson.destination < _currentFloor && !_isGoingUp) )
                {
                  System.out.println("Person going to floor [" + aPerson.destination + "] gets in the lift");
                  _PeopleInLift.add(aPerson);                
                  aWaiting.remove();                
                }
            }
        }

    }

    boolean isGoUpAtExtremities(){
        if (_currentFloor == _maxFloors) {
            System.out.println("Change direction to go down"); 
            return false;            
        }

        if (_currentFloor == 0) {
            System.out.println("Change direction to go up");
            return true;
        }

        return _isGoingUp; // no change
    }

    int getDistance() {
        if (!isPeopleHaveValidDestination()){
            System.out.println("ABORT!!"); 
            return 0;
        }

        while ((_PeopleWaiting.size() > 0) || (_PeopleInLift.size() > 0)){
            removePeopleArrivedAtDestination();
            addPeopleStartingAtThisFloor();            
            if ((_PeopleWaiting.size() > 0) || (_PeopleInLift.size() > 0)){
              move();
            }
        }     

        return _distance;
    }

}