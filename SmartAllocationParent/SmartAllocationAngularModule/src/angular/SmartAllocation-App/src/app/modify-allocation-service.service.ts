import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Allocation } from "./allocation";
import { environment } from "src/environments/environment";
import { UserDataServiceService } from "./user-data-service.service";
import { AllocationChangeRequest } from "./allocationChangeRequest";

@Injectable({
  providedIn: "root"
})
export class ModifyAllocationServiceService {
  static readonly modifyAllocationUrl =
    environment.service_backend_url + "/smartallocation/modify";

  constructor(
    private httpClient: HttpClient,
    private userDataService: UserDataServiceService
  ) {}

  public modifyAllocation(allocationChangeRequest: AllocationChangeRequest): Observable<Object> {
    return this.httpClient.post(
      ModifyAllocationServiceService.modifyAllocationUrl,
      {
        allocation: allocationChangeRequest,
        smartAllocationRequestEntity: {creds: this.userDataService.encodedCreds,
        role: this.userDataService.applicationUser.selectedRole}
      }
    );
  }
}
