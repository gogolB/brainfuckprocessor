package memory

import chisel3._


object MemoryMain extends App {

  // 32K addr, 4 banks, 8 bit word
  iotesters.Driver.execute(args, () => new RAM(32768,4,8)) {
    c => new MemoryUnitTester(c)
  }

  // 32K addr, 1 bank, 16 bit word
  iotesters.Driver.execute(args, () => new RAM(32768,1,16)) {
    c => new MemoryUnitTester1Bank(c)
  }
}


object MemoryRepl extends App {
  iotesters.Driver.executeFirrtlRepl(args, () => new RAM(32768,4,8))
}
