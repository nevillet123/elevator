package app;

import java.util.ArrayList;
import java.util.Iterator;

public class MechanicalElevator extends Elevator {

    MechanicalElevator(int iMaxCapacity, int iMaxFloors, PeopleWaiting iPeopleWaiting){
        super(iMaxCapacity, iMaxFloors, iPeopleWaiting);
    }

    public String getMyElevatorName(){
        return "Mechanical Elevator";
    }

    public void move(){
        if (_currentFloor < _maxFloors && _isGoingUp) {          
          _currentFloor += 1;    
          _distance += 1;     
        //   System.out.println("Move up to floor:" + _currentFloor); 
        }

        if (_currentFloor > 0 && !_isGoingUp) {
            _currentFloor -= 1;
            _distance += 1;
            // System.out.println("Move down to floor:" + _currentFloor); 
        }
        _isGoingUp = isGoUpAtExtremities();        
        
    }

    

}