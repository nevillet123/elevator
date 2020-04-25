package app;

import java.util.ArrayList;
import java.util.Iterator;

public class ServantElevator extends Elevator {

    ServantElevator(int iMaxCapacity, int iMaxFloors, ArrayList<Person> iPeopleWaiting){
        super(iMaxCapacity, iMaxFloors, iPeopleWaiting);
    }



    public void move(){

        if(_PeopleInLift.size() == 0){
         _isGoingUp = !_isGoingUp;
         addPeopleStartingAtThisFloor();
         if(_PeopleInLift.size() == 0){
            _isGoingUp = !_isGoingUp;
         }
        }



        if (_currentFloor < _maxFloors && _isGoingUp) {          
          _currentFloor += 1; 
          _distance += 1;        
          System.out.println("Move up to floor:" + _currentFloor); 
        }

        if (_currentFloor > 0 && !_isGoingUp) {
            _currentFloor -= 1;
            _distance += 1;
            System.out.println("Move down to floor:" + _currentFloor); 
        }



        _isGoingUp = isGoUpAtExtremities();        
        
    }
}