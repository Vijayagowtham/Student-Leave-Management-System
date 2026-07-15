from fpdf import FPDF

class PDF(FPDF):
    def header(self):
        self.set_font('Arial', 'B', 15)
        self.cell(0, 10, 'Student Leave Management System - Test Credentials', 0, 1, 'C')

def create_pdf():
    pdf = PDF()
    pdf.add_page()
    pdf.set_font('Arial', '', 12)
    
    pdf.cell(0, 10, 'Use the following credentials to test the various roles within the system:', 0, 1)
    pdf.ln(5)
    
    data = [
        {"Role": "Administrator", "Email": "admin@school.edu", "Password": "AdminPass123!", "Level": "Full Access (All records, users)", "Dashboard": "admindashboard.html"},
        {"Role": "Tutor", "Email": "tutor@school.edu", "Password": "TutorPass123!", "Level": "Department Access (CS)", "Dashboard": "tutor-dashboard.html"},
        {"Role": "Student", "Email": "student@school.edu", "Password": "StudentPass123!", "Level": "Personal Records only", "Dashboard": "studentdashboard.html"}
    ]
    
    for item in data:
        pdf.set_font('Arial', 'B', 12)
        pdf.cell(0, 8, f"Role: {item['Role']}", 0, 1)
        pdf.set_font('Arial', '', 12)
        pdf.cell(0, 8, f"Email Address: {item['Email']}", 0, 1)
        pdf.cell(0, 8, f"Password: {item['Password']}", 0, 1)
        pdf.cell(0, 8, f"Access Level: {item['Level']}", 0, 1)
        pdf.cell(0, 8, f"Dashboard: {item['Dashboard']}", 0, 1)
        pdf.ln(5)
        
    pdf.output('Test_Credentials.pdf')

if __name__ == '__main__':
    create_pdf()
