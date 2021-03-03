import { Allocation } from "./allocation";

export class AllocationChangeRequest {
  constructor(
    public requestId: String,
    public createdTime: String,
    public clientId: String,
    public clientOrg: String,
    public previousAllocation: Allocation,
    public modifiedAllocation: Allocation,
    public status: String,
    public rejectReason: String,
    public approver: String
  ) {}
}
