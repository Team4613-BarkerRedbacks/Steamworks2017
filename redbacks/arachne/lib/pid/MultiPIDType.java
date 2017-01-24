package redbacks.arachne.lib.pid;

import redbacks.arachne.lib.pid.AcMultiPID.PIDAxis;

//TODO Unused
public interface MultiPIDType
{
	public double getOutput(PIDAxis[] axes);
}
