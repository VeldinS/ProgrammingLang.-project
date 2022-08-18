#include <iostream>
#include <ctime>
#include <chrono>

using namespace std::chrono;


void stackDynamic(){
    int matrix1[100][100];
    int matrix2[100][100];
    int resultMatrix[100][100];

    srand (time(nullptr));

    //Populate both matrices
    for(int i=0;i<100;i++){
        for (int j=0;j<100;j++){
            matrix1[i][j]=rand()%100 +1;
            matrix2[i][j]=rand()%100 +1;
        }
    }


    //Multiplication operations

    for(int i=0;i<100;i++){
        for(int j=0;j<100;j++){
            for(int k=0;k<100;k++){
                resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }

}

void staticVars(){
    static int matrix1[100][100];
    static int matrix2[100][100];
    static int resultMatrix[100][100];

    srand (time(nullptr));

    //Populate both matrices
    for(int i=0;i<100;i++){
        for (int j=0;j<100;j++){
            matrix1[i][j]=rand()%100 +1;
            matrix2[i][j]=rand()%100 +1;
        }
    }

    //Multiplication operations

    for(int i=0;i<100;i++){
        for(int j=0;j<100;j++){
            for(int k=0;k<100;k++){
                resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }
}


int main() {

    auto start = high_resolution_clock::now();
    staticVars();
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<microseconds>(stop - start);
    std::cout <<"Execution time with static variables: " << duration.count() << " microseconds" << std::endl;

    auto start2 = high_resolution_clock::now();
    stackDynamic();
    auto stop2 = high_resolution_clock::now();
    auto duration2 = duration_cast<microseconds>(stop2 - start2);
    std::cout <<"Execution time with stack dynamic variables: " << duration2.count() << " microseconds"<<std::endl;

    return 0;
}
