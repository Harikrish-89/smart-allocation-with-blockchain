import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Allocation } from "./allocation";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { UserDataServiceService } from "./user-data-service.service";
import { AllocationChangeRequest } from "./allocationChangeRequest";

@Injectable({
  providedIn: "root"
})
export class ViewAllocationServiceService {
  constructor(
    private httpClient: HttpClient,
    private userDataService: UserDataServiceService
  ) {}

  static readonly viewAllocationGetUrl =
    environment.service_backend_url + "/smartallocation/view";

  public getAllocation(): Observable<AllocationChangeRequest[]> {
    return this.httpClient.post<AllocationChangeRequest[]>(
      ViewAllocationServiceService.viewAllocationGetUrl,
      {
        creds: this.userDataService.encodedCreds,
        role: this.userDataService.applicationUser.selectedRole,
        clientId: "balachandar.b"
      }
    );
  }
}
