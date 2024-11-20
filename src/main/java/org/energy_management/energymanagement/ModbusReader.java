package org.energy_management.energymanagement;

import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.io.ModbusTCPTransaction;
import com.ghgande.j2mod.modbus.msg.ReadInputRegistersRequest;
import com.ghgande.j2mod.modbus.msg.ReadInputRegistersResponse;
import com.ghgande.j2mod.modbus.net.TCPMasterConnection;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ModbusReader {

    // Modbus TCP parametersd
    private static final String MODBUS_IP = "192.168.1.10"; // IP of your PXr10 relay
    private static final int MODBUS_PORT = 502; // Default Modbus TCP port

    // Array of Unit IDs (Modbus devices) you want to query
    private static final int[] UNIT_IDS = {1, 2, 3}; // Add more Unit IDs as needed

    // Array of specific registers you want to read (starting address for floating-point values)
    private static final int[] REGISTER_ADDRESSES = {406147, 406149, 406151, 406159, 406161, 406163, 406187, 406189, 406191, 406309, 406313, 406329, 406547};


    // Array of scale factors corresponding to each register
    private static final float[] SCALE_FACTORS = {10, 10, 10, 10, 10, 10, 1, 1, 1, 1, 1, 1, 1}; // Example scale factors for each register

    public static void main(String[] args) {
        // ScheduledExecutorService to run the task every 2 minutes
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Task to fetch Modbus data
        Runnable fetchModbusData = () -> {
            try {
                // Step 1: Connect to the Modbus TCP device
                InetAddress address = InetAddress.getByName(MODBUS_IP);
                TCPMasterConnection connection = new TCPMasterConnection(address); // Establish a connection
                connection.setPort(MODBUS_PORT);
                connection.connect(); // Open the connection
                System.out.println("Connected to Modbus device at: " + MODBUS_IP);

                // Step 2: Loop over the Unit IDs (for each Modbus device)
                for (int unitId : UNIT_IDS) {
                    System.out.println("\nReading from Unit ID: " + unitId);

                    // Step 3: Loop over the specific register addresses for floating-point data
                    for (int i = 0; i < REGISTER_ADDRESSES.length; i++) { //aici a fost FLOATING_POINT_REGISTER_ADDRESSES
                        int registerAddress = REGISTER_ADDRESSES[i]; //aici a fost FLOATING_POINT_REGISTER_ADDRESSES
                        float scaleFactor = SCALE_FACTORS[i]; // Get the corresponding scale factor

                        // Read two registers for a single floating-point value
                        ReadInputRegistersRequest request = new ReadInputRegistersRequest(registerAddress, 2); // Reading 2 registers
                        request.setUnitID(unitId); // Set the Modbus Unit ID for each device

                        // Create and execute the Modbus transaction
                        ModbusTCPTransaction transaction = new ModbusTCPTransaction(connection);
                        transaction.setRequest(request);
                        transaction.execute();

                        // Get the response and extract the register values
                        ReadInputRegistersResponse response = (ReadInputRegistersResponse) transaction.getResponse();
                        int highRegister = response.getRegisterValue(0); // First register (high word)
                        int lowRegister = response.getRegisterValue(1);  // Second register (low word)

                        // Combine the two registers into a single 32-bit integer
                        int combinedValue = (highRegister << 16) | (lowRegister & 0xFFFF);

                        // Convert the 32-bit integer to a float using ByteBuffer
                        float rawFloatValue = ByteBuffer.allocate(4)
                                .order(ByteOrder.BIG_ENDIAN) // Set the byte order (endianness)
                                .putInt(combinedValue)       // Put the combined value
                                .getFloat(0);                // Get the float value

                        // Apply the scale factor
                        float scaledValue = rawFloatValue * scaleFactor;

                        // Output the scaled floating-point value
                        System.out.printf("Unit ID %d - Floating Point Address %d: Raw Value = %.2f, Scaled Value = %.2f%n", unitId, registerAddress, rawFloatValue, scaledValue);
                    }
                }

                // Step 4: Close the connection
                connection.close();
                System.out.println("Connection closed.");

            } catch (ModbusException | java.net.UnknownHostException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        // Schedule the task to run every 2 minutes (120 seconds)
        scheduler.scheduleAtFixedRate(fetchModbusData, 0, 2, TimeUnit.MINUTES);
    }
}