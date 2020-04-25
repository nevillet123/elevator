package app;

import java.util.ArrayList;
import java.util.Iterator;

public class OldElevator {
    int _maxFloors;
    int _maxCapacity;
    int _currentFloor = 0;
    boolean _isGoingUp = true;
    int _distance = 0;
    ArrayList<Person> _PeopleInLift;

    OldElevator(int iMaxCapacity, int iMaxFloors){
        _PeopleInLift = new ArrayList<Person>(); 
        _maxCapacity =  iMaxCapacity;
        _maxFloors = iMaxFloors;
    }

    void move(){
        if (_currentFloor < _maxFloors && _isGoingUp) {          
          _currentFloor += 1;         
        //   System.out.println("Move up to floor:" + _currentFloor); 
        }

        if (_currentFloor > 0 && !_isGoingUp) {
            _currentFloor -= 1;
            // System.out.println("Move down to floor:" + _currentFloor); 
        }

        if (_currentFloor == _maxFloors) {
            _isGoingUp = false;            
        }

        if (_currentFloor == 0) {
            _isGoingUp = true;
        }

        _distance += 1;
    }

    boolean isPeopleHaveValidDestination(final ArrayList<Person> iPeopleWaiting){
        Iterator<Person> aWaiting = iPeopleWaiting.iterator();
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

    void addPeopleStartingAtThisFloor(final ArrayList<Person> iPeopleWaiting){
        Iterator<Person> aWaiting = iPeopleWaiting.iterator();
        while (aWaiting.hasNext()) {
            Person aPerson = aWaiting.next();
            if ((aPerson.start == _currentFloor) &&
                (_PeopleInLift.size() < _maxCapacity))
            {     
                //People only enter the lift if going in the right direction
                if ((aPerson.destination > _currentFloor && _isGoingUp) ||
                   (aPerson.destination < _currentFloor && !_isGoingUp))
                {
                  _PeopleInLift.add(aPerson);                
                  aWaiting.remove();                
                }
            }
        }

    }

    int getDistance(final ArrayList<Person> iPeopleWaiting) {
        if (!isPeopleHaveValidDestination(iPeopleWaiting)){
            System.out.println("ABORT!!"); 
            return 0;
        }

        while ((iPeopleWaiting.size() > 0) || (_PeopleInLift.size() > 0)){
            removePeopleArrivedAtDestination();
            addPeopleStartingAtThisFloor(iPeopleWaiting);            
            if ((iPeopleWaiting.size() > 0) || (_PeopleInLift.size() > 0)){
              move();
            }
        }     

        return _distance;
    }

}