#include <stdio.h>
#include <string.h>
#include "map.h"
#include <stdlib.h>
#include <sys/time.h>

int main() {

    map_int_t map;
    map_init(&map);

    srand ( time(NULL) );

    for (int i = 0; i < 100000; ++i) {
        char c[6];
        itoa(i,c,10);
        map_set(&map,c,rand());
    }

    int numbers[100000];

    for (int i = 0; i < 100000; ++i) {
        numbers[i] = rand();
    }

    const char *keys[5];
    keys[0] = "20000";
    keys[1] = "42100";
    keys[2] = "60321";
    keys[3] = "84912";
    keys[4] = "90130";



    clock_t timer;
    timer = clock();

    for (int i = 0; i < 100000; ++i) {
         map_get(&map, keys[i%5]);
    }

    timer = clock() - timer;
    double time_taken = ((double)timer)/CLOCKS_PER_SEC;
    printf("Hashmap took %f seconds to execute \n", time_taken);

    clock_t arrayTimer;
    arrayTimer = clock();

    for (int i = 0; i < 100000 ; ++i) {
        for (int j = 0; j < 100000; ++j) {
            if(numbers[j]==12580){
                break;
            }
        }
    }

    arrayTimer = clock() - arrayTimer;
    double time_elapsed = ((double)arrayTimer)/CLOCKS_PER_SEC;
    printf("Array took %f seconds to execute \n", time_elapsed);

    map_deinit(&map);
}