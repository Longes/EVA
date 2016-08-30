package com.rostlab.generationModule.PDB;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Longes on 27.07.2016.
 */
public class RostLabSSH {

    public static ChannelSftp getPDBFile(String pdbId) {
        String user = "agaltsev";
        String password = "superboy";
        String host = "rostssh.informatik.tu-muenchen.de";
        int port=8574;

        String remoteFile="/mnt/project/rost_db/data/pdb/entries/";

        ChannelSftp sftpChannel = null;
        try
        {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            System.out.println("Connection established.");
            System.out.println("Crating SFTP Channel.");
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            System.out.println("SFTP Channel created.");

            InputStream out= null;
            out= sftpChannel.get(remoteFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(out));
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
            br.close();
        }
        catch(Exception e){System.err.print(e);}
        return sftpChannel;
    }
}
