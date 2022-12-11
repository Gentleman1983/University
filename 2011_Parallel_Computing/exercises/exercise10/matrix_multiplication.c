/*
 * PC_10 Programming Exercise, task 3
 * 19.01.2012
 */

#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>

int main( int argc, char *argv[] )
{
    int rank; /* Rank of process */
    int size; /* Number of processes */
    
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    /* MPI_COMM_WORLD is collection of all processes/tasks */
    
    //Program here ... have fun :-)
    
    
    
    MPI_Finalize();
    return 0;
}
