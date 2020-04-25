package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
// import java.util.List;

import org.junit.jupiter.api.Test;

class ElevatorTest {
    
    ArrayList<Person> _peopleWaiting = new ArrayList<Person>();

    int getDestinationDifferentFromStart(int aMaxFloors, int start){
        int uniqueDestination =  (int) (Math.random() * (aMaxFloors));
        while (uniqueDestination == start){
            uniqueDestination = (int) (Math.random() * (aMaxFloors));
        }
        return uniqueDestination;
    }

// TESTS    

    @Test
    void shouldDoNothingIfNoPeopleWaiting() {        
        MechanicalElevator aOldElevator = new MechanicalElevator(10,10,_peopleWaiting);       
        assertEquals(0, aOldElevator.getDistance());
    }

    @Test
    void shouldMoveOneIfOnePersonFromZeroToFirstFloor() {   
        _peopleWaiting.add(new Person(0,1));
        MechanicalElevator aOldElevator = new MechanicalElevator(1,2,_peopleWaiting);         
        assertEquals(1, aOldElevator.getDistance());
    }

    @Test
    void shouldMoveTwoIfOnePersonFromFirstToSecondFloor() {  
        _peopleWaiting.add(new Person(1,2)); 
        MechanicalElevator aOldElevator = new MechanicalElevator(1,2,_peopleWaiting);  
        assertEquals(2, aOldElevator.getDistance());
    }

    @Test
    void shouldMoveUpAndDownWithOnePersonFromFirstToZero() {  
        _peopleWaiting.add(new Person(1,0)); 
        MechanicalElevator aOldElevator = new MechanicalElevator(1,2,_peopleWaiting);                          
        assertEquals(4, aOldElevator.getDistance());
    }

    @Test
    void shouldMoveUpAndDownWithTwoPersonOneGoingUpOneGoingDown() { 
        _peopleWaiting.add(new Person(0,1));
        _peopleWaiting.add(new Person(1,0));  
        MechanicalElevator aOldElevator = new MechanicalElevator(1,2,_peopleWaiting);            
        assertEquals(4, aOldElevator.getDistance());
    }

    @Test
    void shouldRespectLiftCapacityOfOne() {   
        _peopleWaiting.add(new Person(0,1));
        _peopleWaiting.add(new Person(0,2));
        MechanicalElevator aOldElevator = new MechanicalElevator(1,2,_peopleWaiting);  
        assertEquals(6, aOldElevator.getDistance());
    }

    @Test
    void shouldAllowTwoPeopleInLift() {   
        _peopleWaiting.add(new Person(0,1));
        _peopleWaiting.add(new Person(0,2));
        MechanicalElevator aOldElevator = new MechanicalElevator(2,2,_peopleWaiting);  
        assertEquals(2, aOldElevator.getDistance());
    }

    @Test
    void shouldAssumePeopleDontEnterLiftIfGoingWrongDirection() {   
        _peopleWaiting.add(new Person(1,0));
        _peopleWaiting.add(new Person(1,2));
        MechanicalElevator aOldElevator = new MechanicalElevator(1,2,_peopleWaiting);  
        assertEquals(4, aOldElevator.getDistance());
    }

    @Test
    void shouldPickUpPeopleOnTopFloor() {   
        _peopleWaiting.add(new Person(2,0));        
        MechanicalElevator aOldElevator = new MechanicalElevator(1,2,_peopleWaiting);  
        assertEquals(4, aOldElevator.getDistance());
    }

    @Test
    void shouldCheckPeopleHaveValidDestination() {   
        _peopleWaiting.add(new Person(0,2));
        MechanicalElevator aOldElevator = new MechanicalElevator(1,1,_peopleWaiting);          
        assertEquals(0, aOldElevator.getDistance());
    }



    @Test
    void shouldGetAverageForRamdomTest() { 
        
        int aOverallAverageForServant = 0;
        int aOverallAverageForMechanical = 0;
        int aNumberOfExecutions = 100;
        int aMaxFloors = 10;
                         
        for(int j=0; j<aNumberOfExecutions ; j++){
            ArrayList<Person> aPeopleWaitingForServant = new ArrayList<Person>();
            ArrayList<Person> aPeopleWaitingForMechanical = new ArrayList<Person>();
            Elevator aServantElevator = new ServantElevator(10,aMaxFloors,aPeopleWaitingForServant);
            Elevator aMechanicalElevator = new MechanicalElevator(10,aMaxFloors,aPeopleWaitingForMechanical);

            int aNumberOfPeopleWaiting = 10;
            for(int i=0; i<aNumberOfPeopleWaiting ; i++){
                int aStart = (int) (Math.random() * (aMaxFloors));
                int aDestination = getDestinationDifferentFromStart(aMaxFloors,aStart);
                aPeopleWaitingForServant.add(new Person(aStart,aDestination));
                aPeopleWaitingForMechanical.add(new Person(aStart,aDestination));
            }    
            int aDistanceForServant = aServantElevator.getDistance();
            int aDistanceForMechanical = aMechanicalElevator.getDistance();
            System.out.println("Total Distance  [" + j + "]  for the servant lift is " + aDistanceForServant);
            System.out.println("Total Distance  [" + j + "]  for the mechanical lift is " + aDistanceForMechanical);
            
            aOverallAverageForServant += aDistanceForServant;
            aOverallAverageForMechanical += aDistanceForMechanical;
        }

        aOverallAverageForServant = (int) aOverallAverageForServant / aNumberOfExecutions;
        aOverallAverageForMechanical = (int) aOverallAverageForMechanical / aNumberOfExecutions;

        System.out.println("Average Distance for Servant after [" + aNumberOfExecutions + "] executions [" + aOverallAverageForServant + "]" );
        System.out.println("Average Distance for Mechanical after [" + aNumberOfExecutions + "] executions [" + aOverallAverageForMechanical + "]" );

    }


    // @Test
    // void shouldGoUpIfMorePeopleWantToGoUp() {   
    //     _peopleWaiting.add(new Person(1,0));
    //     _peopleWaiting.add(new Person(1,2));
    //     _peopleWaiting.add(new Person(1,2));
    //     DemocraticElevator aElevator = new DemocraticElevator(10,2,_peopleWaiting);  
    //     assertEquals(4, aElevator.getDistance());
    // }

    
    @Test
    void testIdea() {   
        _peopleWaiting.add(new Person(1,0));
        ServantElevator aElevator = new ServantElevator(10,10,_peopleWaiting);   
        assertEquals(2, aElevator.getDistance());
    }


}



