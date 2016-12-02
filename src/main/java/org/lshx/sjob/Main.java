package org.lshx.sjob;

import org.apache.commons.cli.*;
import org.lshx.sjob.op.QuartzOp;

/**
 * Created by lee5hx on 16/10/25.
 */
public class Main {
    public static void main(String args[]) {

        CommandLineParser parser = new DefaultParser();
        QuartzOp op = new QuartzOp();

        Options options = new Options();
//        options.addOption("",false,"");
        options.addOption("e", false, "List Quartz Executing Jobs");
        options.addOption("a", false, "List Quartz ALL Jobs");
        options.addOption("v", "version", false, "Show the SJob version information ");
        options.addOption("p", "pause", true, "Pause job");
        options.addOption("r", "resume", true, "Resume job");
        options.addOption("d", "delete", true, "Delete job");
        options.addOption("ro", "runOne", true, "Run once job");
        options.addOption("h", "help", false, "Print usage");

        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("e")) {
                op.getJobsByOp("executing-all",true);
            }
            if (line.hasOption("a")) {
                op.getJobsByOp("all",true);
            }
            if (line.hasOption("p")) {
                op.pauseJob(line.getOptionValue("p"));
            }

            if (line.hasOption("d")) {
                op.deleteJob(line.getOptionValue("d"));
            }
            if (line.hasOption("r")) {
                op.resumeJob(line.getOptionValue("r"));
            }
            if (line.hasOption("ro")) {
                op.runOneJob(line.getOptionValue("ro"));
            }
            if (line.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("sjob [options] [<arg>=job_hash] ", null,options,null,false);
            }




            if (line.hasOption("v")) {
                System.out.println("SJob v1.1");
            }
        } catch (ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
        }
    }
}
