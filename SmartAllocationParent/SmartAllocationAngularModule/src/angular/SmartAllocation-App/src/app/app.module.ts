import { BrowserModule } from "@angular/platform-browser";
import { NgModule, ClassProvider } from "@angular/core";
import { FormsModule } from "@angular/forms";

import { AppComponent } from "./app.component";
import { ServiceWorkerModule } from "@angular/service-worker";
import { environment } from "../environments/environment";
import { HeaderComponent } from "./header/header.component";
import { LoginComponent } from "./login/login.component";
import { AppRoutingModule } from ".//app-routing.module";
import { RouterModule, Routes } from "@angular/router";
import { UserHomeComponent } from "./user-home/user-home.component";
import { ViewAllocationComponent } from "./view-allocation/view-allocation.component";
import { HttpClientModule } from "@angular/common/http";
import { MatTableModule } from "@angular/material/table";
import { MatPaginatorModule } from "@angular/material/paginator";
import { ModifyAllocationComponent } from "./modify-allocation/modify-allocation.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatCardModule, MatFormFieldModule, MatInputModule, MatSortModule} from "@angular/material/";
import { ChangeAllocationComponent } from './change-allocation/change-allocation.component';
import { ViewPendingAllocationComponent} from "./pending-allocations/pending-allocations.component";
import { LoggingInterceptorService } from "./logging-interceptor.service";
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { SearchAllocationComponent } from './search-allocation/search-allocation.component';
import { ProfileComponent } from './profile/profile.component';

const appRoutes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "userHome", component: UserHomeComponent },
  { path: "viewAllocation", component: ViewAllocationComponent },
  { path: "modifyAllocation", component: ModifyAllocationComponent },
  { path: "reviewAllocation", component: ViewPendingAllocationComponent},
  { path: "modifyAllocation", component: ModifyAllocationComponent },
  { path: "searchAllocation", component: SearchAllocationComponent },
  { path: "profile" , component: ProfileComponent }
];

const LOGGING_INTERCEPTOR_PROVIDER: ClassProvider = {
  provide: HTTP_INTERCEPTORS ,
  useClass: LoggingInterceptorService,
  multi: true
};

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    UserHomeComponent,
    ViewAllocationComponent,
    ModifyAllocationComponent,
    ChangeAllocationComponent,
    ViewPendingAllocationComponent,
    ChangeAllocationComponent,
    SearchAllocationComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ServiceWorkerModule.register("/ngsw-worker.js", {
      enabled: environment.production
    }),
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSortModule
  ],
  providers: [LOGGING_INTERCEPTOR_PROVIDER],
  bootstrap: [AppComponent]
})
export class AppModule {}
