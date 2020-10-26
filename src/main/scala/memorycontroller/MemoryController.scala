
package memorycontroller

import memory.RAM

import chisel3._
import chisel3.util._


/** 
 * The memorycontroller for the processor implementation.
 */
class MemoryController (n:Int) extends Module {
  val io = IO(new Bundle {
    val ADDR            = Input(UInt(log2Ceil(n).W))

    val WR_DATA         = Input(UInt(32.W))
    val RD_DATA         = Output(UInt(32.W))

    val NUM_WORDS       = Input(UInt(2.W))

    val WR              = Input(Bool())
    val RD              = Input(Bool())

    val isDone          = Output(Bool())
  })

  val mem = new RAM(n)

  object State extends ChiselEnum {
    val sIdle, sRead, sReadDone, sWrite, sWriteDone= Value
  }
  
  val state = RegInit(State.sNone)

  isDone := false.B
  switch (state) {

    // Idle State
    is (State.sIdle) {
      when (io.WR) {
        state := sWrite
      }.elsewhen(io.RD) {
        state := sRead
      }.otherwise {
        state := sIdle
      }
    }

    // PrereadState
    is (State.sRead) {
      isDone := false.B
    }

    // Finished Read State
    is (State.sReadDone) {
      isDone := true.B
    }

    // PreWriteState
    is (State.sWrite) {
      isDone := false.B
    }

    // Finished Write State
    is (State.sWriteDone ) {
      isDone := true.B
    }
  }
}