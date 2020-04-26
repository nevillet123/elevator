package app;

import java.util.ArrayList;
import java.util.Iterator;

public class MinMaxElevator extends Elevator {

    MinMaxElevator(int iMaxCapacity, int iMaxFloors, PeopleWaiting iPeopleWaiting){
        super(iMaxCapacity, iMaxFloors, iPeopleWaiting);
    }

    public String getMyElevatorName(){
        return "MinMax Elevator";
    }

    int getMaxPeopleWaitingStart(){
        int myMax = 0;
        
        for(int i=0; i<_PeopleWaiting.size();i++){
            myMax = Math.max(myMax ,_PeopleWaiting.get(i).start);
        }
        return myMax;
    }

    int getMaxPeopleInLiftDestination(){
        int myMax = 0;
        
        for(int i=0; i<_PeopleInLift.size();i++){
            myMax = Math.max(myMax ,_PeopleInLift.get(i).destination);
        }
        return myMax;
    }

    // void reducedMove(){
    //     if (_currentFloor < Math.min(_maxFloors,Math.max(getMaxPeopleInLiftDestination(), getMaxPeopleWaitingStart())) && _isGoingUp) {          
    //         _currentFloor += 1; 
    //         _distance += 1;        
    //     //   System.out.println("Move up to floor:" + _currentFloor); 
    //     }

    //     if (_currentFloor > 0 && !_isGoingUp) {
    //         _currentFloor -= 1;
    //         _distance += 1;
    //         // System.out.println("Move down to floor:" + _currentFloor); 
    //     }
    // }

    boolean isGoUpAtExtremitiesMinMax(){
        if (_currentFloor == Math.min(_maxFloors,Math.max(getMaxPeopleInLiftDestination(), getMaxPeopleWaitingStart()))) {
            System.out.println("Change direction to go down"); 
            return false;            
        }

        if (_currentFloor == 0) {
            System.out.println("Change direction to go up");
            return true;
        }

        return _isGoingUp; // no change
    }

    public void move(){

        commonMove();
        addPeopleStartingAtThisFloor();
        _isGoingUp = isGoUpAtExtremitiesMinMax();    
           
        
    }
}