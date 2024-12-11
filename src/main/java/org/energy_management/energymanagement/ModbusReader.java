package org.energy_management.energymanagement;

import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.io.ModbusSerialTransaction;
import com.ghgande.j2mod.modbus.msg.ReadInputRegistersRequest;
import com.ghgande.j2mod.modbus.msg.ReadInputRegistersResponse;
import com.ghgande.j2mod.modbus.net.SerialConnection;
import com.ghgande.j2mod.modbus.util.SerialParameters;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ModbusReader {

    // Parametrii Modbus RTU
    private static final String SERIAL_PORT = "COM3"; // Portul serial (de exemplu, COM3 pe Windows)
    private static final int BAUD_RATE = 9600;        // Baud rate-ul serial
    private static final int UNIT_ID = 1;             // ID-ul Unității Modbus (slave)
    private static final int[] REGISTER_ADDRESSES = {0, 2, 4, 6}; // Registrele de citit (exemplu)
    private static final float[] SCALE_FACTORS = {10, 10, 1, 1}; // Factori de scalare pentru fiecare registru

    public static void main(String[] args) {
        SerialConnection connection = null;

        try {
            // Configurarea parametrilor seriali
            SerialParameters params = new SerialParameters();
            params.setPortName(SERIAL_PORT);
            params.setBaudRate(BAUD_RATE);
            params.setDatabits(8);
            params.setParity("None");
            params.setStopbits(1);
            params.setEncoding("rtu"); // Modbus RTU

            // Inițializarea conexiunii seriale
            connection = new SerialConnection(params);
            connection.open();
            System.out.println("Connected to Modbus RTU device via " + SERIAL_PORT);

            // Citirea registrelor
            for (int i = 0; i < REGISTER_ADDRESSES.length; i++) {
                int registerAddress = REGISTER_ADDRESSES[i];
                float scaleFactor = SCALE_FACTORS[i];

                // Pregătirea cererii Modbus
                ReadInputRegistersRequest request = new ReadInputRegistersRequest(registerAddress, 2);
                request.setUnitID(UNIT_ID);

                ModbusSerialTransaction transaction = new ModbusSerialTransaction(connection);
                transaction.setRequest(request);

                // Executarea tranzacției
                transaction.execute();
                ReadInputRegistersResponse response = (ReadInputRegistersResponse) transaction.getResponse();

                // Procesarea valorilor din registre
                int highRegister = response.getRegisterValue(0);
                int lowRegister = response.getRegisterValue(1);

                int combinedValue = (highRegister << 16) | (lowRegister & 0xFFFF);
                float rawFloatValue = ByteBuffer.allocate(4)
                        .order(ByteOrder.BIG_ENDIAN)
                        .putInt(combinedValue)
                        .getFloat(0);

                float scaledValue = rawFloatValue * scaleFactor;

                // Afișarea valorilor
                System.out.printf("Register Address %d: Raw Value = %.2f, Scaled Value = %.2f%n", registerAddress, rawFloatValue, scaledValue);
            }

        } catch (ModbusException e) {
            System.err.println("Modbus exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        }
    }
}
