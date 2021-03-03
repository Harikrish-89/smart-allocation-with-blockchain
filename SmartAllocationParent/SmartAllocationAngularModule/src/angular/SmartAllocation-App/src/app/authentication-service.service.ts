import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { UserDataServiceService } from "./user-data-service.service";
import { ApplicationUser } from "./applicationUser";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class AuthenticationServiceService {
  static readonly approvedAllocationUrl =
    environment.service_backend_url + "/smartallocation/login";

  public isAuthenticationError: boolean;

  constructor(
    private httpClient: HttpClient,
    private userDataService: UserDataServiceService
  ) {}

  public authenticate(userNamePassword: String): Observable<ApplicationUser> {
    return this.httpClient.post<ApplicationUser>(
      AuthenticationServiceService.approvedAllocationUrl,
      userNamePassword
    );
  }
}
