function downloadPDF(tableId, fileName) {
  const { jsPDF } = window.jspdf;
  const doc = new jsPDF();
  const table = document.getElementById(tableId);

  doc.autoTable({ html: table });

  doc.save(fileName + ".pdf");
}