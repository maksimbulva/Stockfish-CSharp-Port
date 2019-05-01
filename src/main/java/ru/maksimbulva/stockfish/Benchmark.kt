﻿using System;
using System.Collections.Generic;

namespace StockFishPortApp_5._0
{
    public sealed partial class Engine
    {
        public static string[] Defaults = new string[] {
            "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
            "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 10",
            "8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 11",
            "4rrk1/pp1n3p/3q2pQ/2p1pb2/2PP4/2P3N1/P2B2PP/4RRK1 b - - 7 19",
            "rq3rk1/ppp2ppp/1bnpb3/3N2B1/3NP3/7P/PPPQ1PP1/2KR3R w - - 7 14",
            "r1bq1r1k/1pp1n1pp/1p1p4/4p2Q/4Pp2/1BNP4/PPP2PPP/3R1RK1 w - - 2 14",
            "r3r1k1/2p2ppp/p1p1bn2/8/1q2P3/2NPQN2/PPP3PP/R4RK1 b - - 2 15",
            "r1bbk1nr/pp3p1p/2n5/1N4p1/2Np1B2/8/PPP2PPP/2KR1B1R w kq - 0 13",
            "r1bq1rk1/ppp1nppp/4n3/3p3Q/3P4/1BP1B3/PP1N2PP/R4RK1 w - - 1 16",
            "4r1k1/r1q2ppp/ppp2n2/4P3/5Rb1/1N1BQ3/PPP3PP/R5K1 w - - 1 17",
            "2rqkb1r/ppp2p2/2npb1p1/1N1Nn2p/2P1PP2/8/PP2B1PP/R1BQK2R b KQ - 0 11",
            "r1bq1r1k/b1p1npp1/p2p3p/1p6/3PP3/1B2NN2/PP3PPP/R2Q1RK1 w - - 1 16",
            "3r1rk1/p5pp/bpp1pp2/8/q1PP1P2/b3P3/P2NQRPP/1R2B1K1 b - - 6 22",
            "r1q2rk1/2p1bppp/2Pp4/p6b/Q1PNp3/4B3/PP1R1PPP/2K4R w - - 2 18",
            "4k2r/1pb2ppp/1p2p3/1R1p4/3P4/2r1PN2/P4PPP/1R4K1 b - - 3 22",
            "3q2k1/pb3p1p/4pbp1/2r5/PpN2N2/1P2P2P/5PP1/Q2R2K1 b - - 4 26",
            "6k1/6p1/6Pp/ppp5/3pn2P/1P3K2/1PP2P2/3N4 b - - 0 1",
            "3b4/5kp1/1p1p1p1p/pP1PpP1P/P1P1P3/3KN3/8/8 w - - 0 1",
            "2K5/p7/7P/5pR1/8/5k2/r7/8 w - - 0 1",
            "8/6pk/1p6/8/PP3p1p/5P2/4KP1q/3Q4 w - - 0 1",
            "7k/3p2pp/4q3/8/4Q3/5Kp1/P6b/8 w - - 0 1",
            "8/2p5/8/2kPKp1p/2p4P/2P5/3P4/8 w - - 0 1",
            "8/1p3pp1/7p/5P1P/2k3P1/8/2K2P2/8 w - - 0 1",
            "8/pp2r1k1/2p1p3/3pP2p/1P1P1P1P/P5KR/8/8 w - - 0 1",
            "8/3p4/p1bk3p/Pp6/1Kp1PpPp/2P2P1P/2P5/5B2 b - - 0 1",
            "5k2/7R/4P2p/5K2/p1r2P1p/8/8/8 b - - 0 1",
            "6k1/6p1/P6p/r1N5/5p2/7P/1b3PP1/4R1K1 w - - 0 1",
            "1r3k2/4q3/2Pp3b/3Bp3/2Q2p2/1p1P2P1/1P2KP2/3N4 w - - 0 1",
            "6k1/4pp1p/3p2p1/P1pPb3/R7/1r2P1PP/3B1P2/6K1 w - - 0 1",
            "8/3p3B/5p2/5P2/p7/PP5b/k7/6K1 w - - 0 1"
        };

        /// benchmark() runs a simple benchmark by letting Stockfish analyze a set
        /// of positions for a given limit each. There are five parameters: the
        /// transposition table size, the number of search threads that should
        /// be used, the limit value spent for each position (optional, default is
        /// depth 13), an optional file name where to look for positions in FEN
        /// format (defaults are the positions defined above) and the type of the
        /// limit value: depth (default), time in secs or number of nodes.
        public static void benchmark(Position current, Stack<string> stack)
        {
            LimitsType limits = new LimitsType();
            List<string> fens = new List<string>();

            // Assign default values to missing arguments
            string ttSize = (stack.Count > 0) ? (stack.Pop()) : "32";
            string threads = (stack.Count > 0) ? (stack.Pop()) : "1";
            string limit = (stack.Count > 0) ? (stack.Pop()) : "14";
            string fenFile = (stack.Count > 0) ? (stack.Pop()) : "default";
            string limitType = (stack.Count > 0) ? (stack.Pop()) : "depth";
            string cantidad = (stack.Count > 0) ? (stack.Pop()) : "20";
            string desde = (stack.Count > 0) ? (stack.Pop()) : "1";

            //string desde = (stack.Count > 0) ? (stack.Pop()) : "1";
            //string cantidad = (stack.Count > 0) ? (stack.Pop()) : "100000";
            //string limit = (stack.Count > 0) ? (stack.Pop()) : "21";
            //string fenFile = (stack.Count > 0) ? (stack.Pop()) : "default";
            //string ttSize = (stack.Count > 0) ? (stack.Pop()) : "32";
            //string threads = (stack.Count > 0) ? (stack.Pop()) : "1";                        
            //string limitType = (stack.Count > 0) ? (stack.Pop()) : "depth";


            Options["Hash"].setCurrentValue(ttSize);
            Options["Threads"].setCurrentValue(threads);
            TT.clear();

            if (limitType == "time")
                limits.movetime = 1000 * int.Parse(limit); // movetime is in ms

            else if (limitType == "nodes")
                limits.nodes = int.Parse(limit);

            else if (limitType == "mate")
                limits.mate = int.Parse(limit);

            else
                limits.depth = int.Parse(limit);

            if (fenFile == "default")
                fens.AddRange(Defaults);

            else if (fenFile == "current")
                fens.Add(current.fen());

            else
            {
                String fen;
                System.IO.StreamReader sr = new System.IO.StreamReader(fenFile, true);
                int leidos = 0;
                int ingresados = 0;
                int init = Int32.Parse(desde);
                int n = Int32.Parse(cantidad);
                while (!sr.EndOfStream)
                {
                    fen = sr.ReadLine().Trim();
                    leidos++;

                    if (leidos >= init && ingresados <= n)
                    {
                        fens.Add(fen);
                        ingresados++;
                        if (ingresados >= n)
                            break;
                    }
                }

                sr.Close();
                sr.Dispose();

            }

            Int64 nodes = 0;
            StateStackPtr st = new StateStackPtr();
            long elapsed = Time.now();

            for (int i = 0; i < fens.Count; ++i)
            {
                //time.Reset(); time.Start();
                Position pos = new Position(fens[i], Options["UCI_Chess960"].getInt()!=0 ? 1 : 0, Threads.main());

                inOut.Write(Types.newline + "Position: " + (i + 1).ToString() + "/" + fens.Count.ToString() + Types.newline);
                                                
                inOut.Write(": ");
                inOut.Write(fens[i]);
                inOut.Write(Types.newline);
                if (limitType == "divide") {
                    /**
                     * for (MoveList<LEGAL> it(pos); *it; ++it)
                      {
                          StateInfo si;
                          pos.do_move(*it, si);
                          uint64_t cnt = limits.depth > 1 ? Search::perft(pos, (limits.depth - 1) * ONE_PLY) : 1;
                          pos.undo_move(*it);
                          cerr << move_to_uci(*it, pos.is_chess960()) << ": " << cnt << endl;
                          nodes += cnt;
                      }
                     */
                }if (limitType == "perft")
                {
                    /**
                     uint64_t cnt = Search::perft(pos, limits.depth * ONE_PLY);
                    cerr << "\nPerft " << limits.depth  << " leaf nodes: " << cnt << endl;
                        nodes += cnt;
                     */
                }
                else
                {
                    Engine.Threads.start_thinking(pos, limits, st);
                    Threads.wait_for_think_finished();
                    nodes += (Int64)Search.RootPos.nodes_searched();
                }
            }

            elapsed = Time.now() - elapsed + 1; // Assure positive to avoid a 'divide by zero'

            inOut.Write(Types.newline + "===========================");
            inOut.Write(Types.newline + "Total time (ms) : " + elapsed.ToString());
            inOut.Write(Types.newline + "Nodes searched  : " + nodes.ToString());
            inOut.Write(Types.newline + "Nodes/second    : " + (1000 * nodes / elapsed).ToString() + Types.newline);                                    
        }


        public static void benchfile(Position current, Stack<string> stack)
        {
            LimitsType limits = new LimitsType();

            // Assign default values to missing arguments
            //string ttSize = (stack.Count > 0) ? (stack.Pop()) : "32";
            //string threads = (stack.Count > 0) ? (stack.Pop()) : "1";
            //string limit = (stack.Count > 0) ? (stack.Pop()) : "14";
            //string fenFile = (stack.Count > 0) ? (stack.Pop()) : "default";
            //string limitType = (stack.Count > 0) ? (stack.Pop()) : "depth";
            //string cantidad = (stack.Count > 0) ? (stack.Pop()) : "20";

            string desde = (stack.Count > 0) ? (stack.Pop()) : "17771192";
            string limit = (stack.Count > 0) ? (stack.Pop()) : "1";
            string fenFile = (stack.Count > 0) ? (stack.Pop()) : "c:\\fen\\unique00.fen";
            string desFile = (stack.Count > 0) ? (stack.Pop()) : fenFile + ".dat2";
            string ttSize = (stack.Count > 0) ? (stack.Pop()) : "32";
            string threads = (stack.Count > 0) ? (stack.Pop()) : "1";
            string limitType = (stack.Count > 0) ? (stack.Pop()) : "depth";


            Options["Hash"].setCurrentValue(ttSize);
            Options["Threads"].setCurrentValue(threads);
            TT.clear();

            if (limitType == "time")
                limits.movetime = 1000 * int.Parse(limit); // movetime is in ms

            else if (limitType == "nodes")
                limits.nodes = int.Parse(limit);

            else if (limitType == "mate")
                limits.mate = int.Parse(limit);

            else
                limits.depth = int.Parse(limit);


            String fen;
            System.IO.StreamReader sr = new System.IO.StreamReader(fenFile, true);
            System.IO.StreamWriter outfile = new System.IO.StreamWriter(desFile, false);

            Int64 i = 0;
            Int64 nodes = 0;
            StateStackPtr st = new StateStackPtr();
            //Stopwatch time = new Stopwatch();
            long elapsed = Time.now();
            int inicio = Int32.Parse(desde);
            while (!sr.EndOfStream)
            {
                fen = sr.ReadLine().Trim();
                i++;
                if (i < inicio)
                    continue;
                Position pos = new Position(fen, Options["UCI_Chess960"].getInt()!=0 ? 1 : 0, Threads.main());

                inOut.Write(Types.newline);
                inOut.Write("Position: ");
                inOut.Write((i + 1).ToString());
                inOut.Write(": ");
                inOut.Write(fen);
                inOut.Write(Types.newline);

                if (limitType == "perft")
                {
                    //int cnt = Search.perft(pos, limits.depth * DepthS.ONE_PLY);
                    //inOut.Write(Types.newline);
                    //inOut.Write("Perft ");
                    //inOut.Write(limits.depth.ToString());
                    //inOut.Write(" leaf nodes: ");
                    //inOut.Write(cnt.ToString());
                    //inOut.Write(Types.newline);
                    //nodes += cnt;
                }
                else
                {
                    Engine.Threads.start_thinking(pos, limits, st);
                    Threads.wait_for_think_finished();
                    nodes += (Int64)Search.RootPos.nodes_searched();
                    outfile.WriteLine(i + "\t" + Search.RootPos.nodes_searched() + "\t" + Search.RootMoves[0].pv[0] + "\t" + Search.RootMoves[0].pv[1] + "\t" + nodes + "\t" + fen);

                }

                if (i % 10000 == 0)
                {
                    outfile.Flush();
                    inOut.Write(Types.newline);
                    inOut.Write(i.ToString());
                }
            }

            sr.Close();
            sr.Dispose();
            outfile.Close();
            outfile.Dispose();


            elapsed = Time.now() - elapsed + 1; // Assure positive to avoid a 'divide by zero'

            inOut.Write(Types.newline);
            inOut.Write("===========================");

            inOut.Write(Types.newline);
            inOut.Write("Total time (ms) : ");
            inOut.Write(elapsed.ToString());

            inOut.Write(Types.newline);
            inOut.Write("Nodes searched  : ");
            inOut.Write(nodes.ToString());

            inOut.Write(Types.newline);
            inOut.Write("Nodes/second    : ");
            inOut.Write((1000 * nodes / elapsed).ToString());
            inOut.Write(Types.newline);
        }

    }
}
