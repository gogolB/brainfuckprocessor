
package memorycontroller

import memory.RAM

import chisel3._
import chisel3.util._


/** 
 * The memorycontroller for the processor implementation.
 */
class MemoryController (nAddr:Int, nWordSize:Int) extends Module {
  val io = IO(new Bundle {
    val ADDR            = Input(UInt(log2Ceil(nAddr).W))

    val WR_DATA         = Input(UInt(32.W))
    val RD_DATA         = Output(UInt(32.W))

    val WR              = Input(Bool())
    val RD              = Input(Bool())

    val isDone          = Output(Bool())
  })
}