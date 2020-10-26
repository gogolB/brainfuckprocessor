
package memorycontroller

import memory.RAM

import chisel3._
import chisel3.util._


/** 
 * The memorycontroller for the processor implementation.
 */
class MemoryController (n:Int) extends Module {
  val io = IO(new Bundle {
    val ADDR         = Input(UInt(log2Ceil(n).W))

    val WR_DATA         = Input(UInt(32.W))
    val RD_DATA         = Output(UInt(32.W))

    val NUM_WORDS       = Input(UInt(2.W))

    val WR              = Input(Bool())

    val isValid         = Output(Bool())
  })

  val mem = new RAM(n)

    
    when(io.WR) {
        // We are in read mode.
        io.isValid = false


        io.isValid = true

    }.otherwise {
        // We are in read mode.
        io.isValid = false

        io.isValid = true
    }


}