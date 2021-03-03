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
export class ApprovedAllocationServiceService {
  static readonly approvedAllocationUrl =
    environment.service_backend_url + "/smartallocation/getLatestAllocations";

  constructor(
    private httpClient: HttpClient,
    private userDataService: UserDataServiceService
  ) {}

  public getApprovedAllocations(): Observable<AllocationChangeRequest[]> {
    return this.httpClient.post<AllocationChangeRequest[]>(
      ApprovedAllocationServiceService.approvedAllocationUrl,
      {
        creds: this.userDataService.encodedCreds,
        role: this.userDataService.applicationUser.selectedRole,
        org: this.userDataService.applicationUser.organisation
      }
    );
  }
}
