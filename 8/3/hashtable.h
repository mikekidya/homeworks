#pragma once

#include <fstream>
#include "string.h"


struct HastTable;

HastTable *createTable();
void deleteTable(HastTable *hastTable);

void add(HastTable *hastTable, String *string);
bool contains(HastTable *hastTable, String *string);

double loadFactor(HastTable *hastTable);
void printElements(HastTable *hastTable, std::ostream &stream);