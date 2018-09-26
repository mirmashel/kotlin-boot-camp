#define _WIN32_WINNT 0x0500
#include <iostream>
#include <windows.h>
#include <stdio.h>
#include <string.h>
#include "okno.cpp"
#include <conio.h>
#include "zmeya.cpp"
using namespace std;

int main()
{
    HWND hWnd;
    HDC hDC;
    int x, y;
    int slojn = 0;
    while (slojn < 1 || slojn > 10)
    {
        cout << "Vvedite slojnost'(1-10): ";
        if (scanf("%d", &slojn) != 1)
        {
            cout << "Vi debil\nPress esc to exit";
            while (1)
                if (getch() == 27)
                    GenerateConsoleCtrlEvent(CTRL_BREAK_EVENT,0);
        }
    }
    if (init_wind(hDC, hWnd, x, y) ==  1)
        GenerateConsoleCtrlEvent(CTRL_BREAK_EVENT,0);
    switch (slojn)
    {
    case 1:
        slojn = 1000;
        break;
    case 2:
        slojn = 500;
        break;
    case 3:
        slojn = 400;
        break;
    case 4:
        slojn = 300;
        break;
    case 5:
        slojn = 250;
        break;
    case 6:
        slojn = 200;
        break;
    case 7:
        slojn = 150;
        break;
    case 8:
        slojn = 100;
        break;
    case 9:
        slojn = 50;
        break;
    case 10:
        slojn = 25;
        break;
    }
    int napr = RIGHT;
    zmeyka *pzmeya = new zmeyka(&hDC, &hWnd, x, y);
    while (1)
    {
        if (_kbhit())
        {
            int knop = getch();
            switch (knop)
            {
            case 27:
                return 0;
            case 230:
                if (napr != DOWN)
                    napr = UP;
                break;
            case 162:
                if (napr != LEFT)
                    napr = RIGHT;
                break;
            case 235:
                if (napr != UP)
                    napr = DOWN;
                break;
            case 228:
                if (napr != RIGHT)
                    napr = LEFT;
                break;
            }

        }
        int lose = pzmeya->dviglo_zm(napr);
        if (lose == 1)
        {
            HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
            COORD cor = {0,0};
            cor.X = 1;
            cor.Y = 1;
            SetConsoleCursorPosition(hConsole,cor);
            printf("You loooose.. with score %d\nPress esc to exit", pzmeya->getscore());
            break;
        }
        if (lose == 2)
        {
            printf("You win!!!!\nPress esc to exit");
            break;
        }
        Sleep(slojn);
    }

    while (1)
        if (getch() == 27)

            break;

    ReleaseDC(hWnd, hDC);
    delete (pzmeya);
    GenerateConsoleCtrlEvent(CTRL_BREAK_EVENT,0);
    return 0;
}
