; P5 Assignment
; Author: Mingtian Gong
; Date:   10/12/2020
; Email:  emoyo@rams.colostate.edu
; Class:  CS270
;
; Description: Implements the arithmetic, bitwise, and shift operations

;------------------------------------------------------------------------------
; Begin reserved section: do not change ANYTHING in reserved section!

                .ORIG x3000
                BR Main

Functions       .FILL IntAdd         ; Address of IntAdd routine     (option 0)
                .FILL IntSub         ; Address of IntSub routine     (option 1)
                .FILL IntMul         ; Address of IntMul routine     (option 2)
                .FILL BinaryOr       ; Address of BinaryOr routine   (option 3)
                .FILL LeftShift      ; Address of LeftShift routine  (option 4)
                .FILL RightShift     ; Address of RightShift routine (option 5)

Main            LEA R0,Functions     ; The main routine calls one of the 
                LD  R1,Option        ; subroutines below based on the value of
                ADD R0,R0,R1         ; the Option parameter.
                LDR R0,R0,0          ;
                JSRR R0              ;
EndProg         HALT                 ;

; Parameters and return values for all functions
; Try changing the .BLKW 1 to .FILL xNNNN where N is a hexadecimal value or #NNNN
; where N is a decimal value, this can save you time by not having to set these 
; values in the simulator every time you run your code. This is the only change 
; you should make to this section.
Option          .FILL #0             ; Which function to call
Param1          .BLKW 1              ; Space to specify first parameter
Param2          .BLKW 1              ; Space to specify second parameter
Result          .BLKW 1              ; Space to store result

; End reserved section: do not change ANYTHING in reserved section!
;------------------------------------------------------------------------------

; You may add variables and functions after here as you see fit.

;------------------------------------------------------------------------------
IntAdd                               ; Result is Param1 + Param2
	LD R1,Param1
	LD R2,Param2
	ADD R1,R1,R2
	ST R1,Result                             ; Your code goes here (~4 lines)
                RET
;------------------------------------------------------------------------------
IntSub                               ; Result is Param1 - Param2
	LD R1,Param1
	LD R2,Param2
	NOT R2,R2
	ADD R2,R2,#1
	ADD R1,R1,R2
	ST R1,Result                 ; Your code goes here (~6 lines)
                RET
;------------------------------------------------------------------------------
IntMul                               ; Result is Param1 * Param2
	AND R1,R1,#0
	LD R2,Param1
	BRz End

	LD R3,Param2
	BRz End

	Loop ADD R1,R1,R2
	ADD R3,R3,#-1
	BRp Loop

	End ST R1,Result                             ; Your code goes here (~9 lines)
                RET
;------------------------------------------------------------------------------
BinaryOr                             ; Result is Param1 | Param2
	LD R1,Param1
	LD R2,Param2
	AND R3,R3,#0
	NOT R1,R1
	NOT R2,R2
	AND R3,R1,R2
	NOT R3,R3
	ST R3,Result                             ; Your code goes here (~7 lines)
                RET
;------------------------------------------------------------------------------
LeftShift                            ; Result is Param1 << Param2
        LD R1,Param1
	LD R2,Param2
	AND R2,R2,#0

	PositiveLoop ADD R1,R1,R1
	ADD R2,R2,#-1
	BRp PositiveLoop

	ST R1,Result
	                             ; (Fill vacant positions with 0's)
                                     ; Your code goes here (~7 lines)
                RET
;------------------------------------------------------------------------------
RightShift                           ; Result is Param1 >> Param2
                                     ; (Fill vacant positions with 0's)
                                     ; Your code goes here (~16 lines)
                RET
;------------------------------------------------------------------------------
                .END
