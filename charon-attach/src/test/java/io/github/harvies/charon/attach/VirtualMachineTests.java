package io.github.harvies.charon.attach;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class VirtualMachineTests {
    @Test
    public void list() {
        List<VirtualMachineDescriptor> virtualMachineDescriptorList = VirtualMachine.list();
        virtualMachineDescriptorList.forEach(System.out::println);
    }

    @Test
    public void attach() throws IOException, AttachNotSupportedException {
        VirtualMachine virtualMachine = VirtualMachine.attach("11247");
        System.err.println(virtualMachine.getAgentProperties());
        System.err.println(virtualMachine.getSystemProperties());
    }
}
