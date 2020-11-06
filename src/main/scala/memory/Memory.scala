
package memory

import chisel3._
import chisel3.util._


/** 
 * The RAM for the processor implementation.
 * nAddr: The number of addresses.
 * nBank: The number of memoryBanks.
 * nWordSize: The wordsize.
 */
class RAM (nAddr:UInt, nBank:UInt, nWordSize:Uint) extends Module {
  val io = IO(new Bundle {
    val WR_DATA         = Input(Vec(nBank, UInt(nWordSize.W)))
    val WR              = Input(Bool())
    val WR_ADDR         = Input(UInt((log2Ceil(nAddr)-log2(nBank)).W))
    val RD_ADDR         = Input(UInt((log2Ceil(nAddr)-log2(nBank)).W))
    val RD              = Input(Bool())
    val MASK            = Input(Vec(nBank, Bool()))

    val RD_DATA         = Output(Vec(nBank, UInt(nWordSize.W)))
  })

  val mem = SyncReadMem(nAddr/nBank, Vec(nBank, UInt(nWordSize.W)))

  when(io.WR) {
    mem.write(io.WR_ADDR, io.WR_DATA, io.MASK)
  }
  when(io.RD){
    io.RD_DATA := mem.read(io.RD_ADDR, io.RD)
  }.otherwise{
      io.RD_DATA := DontCare
  }
}
