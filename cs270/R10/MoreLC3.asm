; Recitation 10
; Author: Mingtian Gong
; Date:   10/9/2020
; Email:  emoyo@rams.colostate.edu
; Class:  CS270
; Description: copies least significant byte to most significant
;--------------------------------------------------------------------------
; Begin reserved section: do not change ANYTHING in reserved section!

                .ORIG x3000

                JSR copy           ; call function
                HALT

; Parameter and return value
; Try changing Param's .BLKW 1 to .FILL xNNNN where N is a hexadecimal value or #NNNN
; where N is a decimal value, this can save you time by not having to set these 
; values in the simulator every time you run your code. This is the only change 
; you should make to this section.
Param           .BLKW 1              ; space to specify parameter
Result          .BLKW 1              ; space to store result

; Constants, the use of One and Eight is optional
One             .FILL #1             ; the number 1   
Eight           .FILL #8             ; the number 8
Mask            .FILL x00ff          ; mask top bits

; End reserved section: do not change ANYTHING in reserved section!
;--------------------------------------------------------------------------
copy                                 ; Copy bits 7:0 to 15:8
                                     ; ~20 lines of assembly code
 
                LD R0,Param          ; load pattern
                ADD R1,R0,#0         ; your code here
		LD R2,Mask
		AND R1,R1,R2
		LD R2,One
		LD R3,One
		LD R4,Eight
Shift8
		ADD R3,R3,R3
		ADD R4,R4,#-1
		BRp Shift8
		LD R4,Eight
		AND R5,R2,R0
		BRz mirror
		ADD R1,R1,R3
mirror
		ADD R2,R2,R2
		ADD R3,R3,R3
		ADD R4,R4,R4
		BRp checkBit	
                ST R1,Result         ; store result
                RET
;--------------------------------------------------------------------------
               .END

