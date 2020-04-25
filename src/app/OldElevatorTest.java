package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
// import java.util.List;

import org.junit.jupiter.api.Test;

class OldElevatorTest {
    
    ArrayList<Person> _peopleWaiting = new ArrayList<Person>();

    int getDestinationDifferentFromStart(int start){
        int uniqueDestination =  (int) (Math.random() * (10));
        while (uniqueDestination == start){
            uniqueDestination = (int) (Math.random() * (10));
        }
        return uniqueDestination;
    }

// TESTS    

    @Test
    void shouldDoNothingIfNoPeopleWaiting() {        
        OldElevator aOldElevator = new OldElevator(10,10);       
        assertEquals(0, aOldElevator.getDistance(_peopleWaiting));
    }

    @Test
    void shouldMoveOneIfOnePersonFromZeroToFirstFloor() {   
        OldElevator aOldElevator = new OldElevator(1,2);  
        _peopleWaiting.add(new Person(0,1));
        assertEquals(1, aOldElevator.getDistance(_peopleWaiting));
    }

    @Test
    void shouldMoveTwoIfOnePersonFromFirstToSecondFloor() {   
        OldElevator aOldElevator = new OldElevator(1,2);  
        _peopleWaiting.add(new Person(1,2));
        assertEquals(2, aOldElevator.getDistance(_peopleWaiting));
    }

    @Test
    void shouldMoveUpAndDownWithOnePersonFromFirstToZero() {  
        OldElevator aOldElevator = new OldElevator(1,2);     
        _peopleWaiting.add(new Person(1,0));               
        assertEquals(4, aOldElevator.getDistance(_peopleWaiting));
    }

    @Test
    void shouldMoveUpAndDownWithTwoPersonOneGoingUpOneGoingDown() {  
        OldElevator aOldElevator = new OldElevator(1,2);     
        _peopleWaiting.add(new Person(0,1));
        _peopleWaiting.add(new Person(1,0));        
        assertEquals(4, aOldElevator.getDistance(_peopleWaiting));
    }

    @Test
    void shouldRespectLiftCapacityOfOne() {   
        OldElevator aOldElevator = new OldElevator(1,2);  
        _peopleWaiting.add(new Person(0,1));
        _peopleWaiting.add(new Person(0,2));
        assertEquals(6, aOldElevator.getDistance(_peopleWaiting));
    }

    @Test
    void shouldAllowTwoPeopleInLift() {   
        OldElevator aOldElevator = new OldElevator(2,2);  
        _peopleWaiting.add(new Person(0,1));
        _peopleWaiting.add(new Person(0,2));
        assertEquals(2, aOldElevator.getDistance(_peopleWaiting));
    }

    @Test
    void shouldAssumePeopleDontEnterLiftIfGoingWrongDirection() {   
        OldElevator aOldElevator = new OldElevator(1,2);  
        _peopleWaiting.add(new Person(1,0));
        _peopleWaiting.add(new Person(1,2));
        assertEquals(4, aOldElevator.getDistance(_peopleWaiting));
    }

    @Test
    void shouldCheckPeopleHaveValidDestination() {   
        OldElevator aOldElevator = new OldElevator(1,1);          
        _peopleWaiting.add(new Person(0,2));
        assertEquals(0, aOldElevator.getDistance(_peopleWaiting));
    }



    @Test
    void shouldGetAverageForRamdomTest() { 
        
        int aOverallAverage = 0;
        int aNumberOfExecutions = 100;
                         
        for(int j=0; j<aNumberOfExecutions ; j++){
            OldElevator aOldElevator = new OldElevator(10,10);
            ArrayList<Person> aPeopleWaiting = new ArrayList<Person>();

            int aNumberOfPeopleWaiting = 100;
            for(int i=0; i<aNumberOfPeopleWaiting ; i++){
                int aStart = (int) (Math.random() * (10));
                int aDestination = getDestinationDifferentFromStart(aStart);
                aPeopleWaiting.add(new Person(aStart,aDestination));
            }    
            int aDistance = aOldElevator.getDistance(aPeopleWaiting);
            System.out.println("Total Distance [" + j + "] " + aDistance);

            aOverallAverage += aDistance;
        }

        aOverallAverage = (int) aOverallAverage / aNumberOfExecutions;

        System.out.println("Average Distance for [" + aNumberOfExecutions + "] executions [" + aOverallAverage + "]" );

    }



}

