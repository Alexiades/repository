package Interface;

import external_file.PropertiesOracle;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileBased;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.configuration2.sync.ReadWriteSynchronizer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Configuration {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField4;
    private JButton returnButton;
    private JButton applyButton;
    private JPasswordField passwordField1;
    private JPanel interfazConf;
    public static FileBasedConfigurationBuilder<FileBasedConfiguration> builder;
    static JFrame frame = new JFrame("interfazConf");


    public Configuration() {

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MainInterface main = new MainInterface();
                main.visible();
                //   inactivarBotones(ejecutarButton, empleadoCreadoButton, jobButton, ausenciasButton, ausenciaModificadaButton, asignacionCreadaButton, asignacionModificadaButton, contratoCreadoButton, contratoModificadoButton, empleadoModificadoButton, empleadoTerminadoButton, locationButton, positionButton, organizationButton, compensaciónVariableButton, compensacionfijaButton, nominaButton);
                //   Acciones.EmpleadoCreado(false);
            }
        });

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                org.apache.commons.configuration2.Configuration config=configPara();

                    config.setProperty("endpoint", textField1.getText());
                    config.setProperty("user", textField2.getText());
                    config.setProperty("password", passwordField1.getPassword());
                    config.setProperty("path", textField4.getText());


                // Create a file handler and associate it with the configuration
                FileHandler handler = new FileHandler((FileBased) config);
/*
// Load another configuration source, for instance from a relative path
                try {

                    handler.load("oracle.properties");
                    // Store the resulting configuguration in a new file
                    File out = new File("prueba.properties");
                    handler.save(out);

                } catch (ConfigurationException e1) {
                    e1.printStackTrace();
                }
*/

                // Make changes persistent
                    try
                    {
                        builder.save();
                    }
                    catch(ConfigurationException cex)
                    {
                        // saving of the configuration file failed
                    }






                //  props.setProperty("user", textField1.getText());

            }
        });
    }

    public org.apache.commons.configuration2.Configuration configPara() {

        Parameters params = new Parameters();
        org.apache.commons.configuration2.Configuration config = null;

        try
        {
        File propertiesFile = new File("oracle.properties");
        builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class).configure(params.properties().setFile(propertiesFile));
            config = builder.getConfiguration();
            return config;
        }
        catch(ConfigurationException cex)
        {
            cex.printStackTrace();
        }
        return config;
    }

    public void visible(){

        org.apache.commons.configuration2.Configuration config=configPara();

               if(config != null){

                   //config.setProperty("",

                   //System.out.println(config.getString("endpoint"));

                  textField1.setText(config.getString("endpoint"));

                   //config.setProperty("user", textField2.getText());
                   //textField1.setText(config.getString("user").toString());
                  // config.setProperty("password", passwordField1.getPassword());
                  // config.setProperty("path", textField4.getText());


        }
        frame.setContentPane(new Configuration().interfazConf);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);  // *** this will center your app ***
        frame.setVisible(true);

    }
}