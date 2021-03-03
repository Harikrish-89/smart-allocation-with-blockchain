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
export class ViewPendingAllocationServiceService {
  constructor(private httpClient: HttpClient,
    private userDataService: UserDataServiceService) {}

  static readonly viewPendingAllocationGetUrl =
  environment.service_backend_url +"/smartallocation/pending";

    public getPendingAllocations(): Observable<AllocationChangeRequest[]> {
    return this.httpClient.post<AllocationChangeRequest[]>(
      ViewPendingAllocationServiceService.viewPendingAllocationGetUrl,
      {
        creds: this.userDataService.encodedCreds,
        role: this.userDataService.applicationUser.selectedRole
      }
    );
  }
}
