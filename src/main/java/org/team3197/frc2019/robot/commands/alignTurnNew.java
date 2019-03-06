 private void getContourParameters() {
        Number[] defaultValues = new Number[] {};
        Number[] contourXs = contourXsEntry.getNumberArray(defaultValues);
        Number[] contourAreas = contourAreasEntry.getNumberArray(defaultValues);
        boolean direction = Math
                .abs(contourXs[0].doubleValue() + contourXs[1].doubleValue()) > RobotMap.cameraPixelWidth;

        if (contourXs.length == contourAreas.length) {
            switch (contourXs.length) {
            case (0):
                // TODO Add rumble
                break;
            case (1):
                driveTrain.tankDrive(.5 * turnDirection, -.5 * turnDirection);
                break;
            case (2):
                double totalArea = contourAreas[0].doubleValue() + contourAreas[1].doubleValue();
                driveTrain.tankDrive(contourAreas[1].doubleValue() / totalArea,
                        contourAreas[0].doubleValue() / totalArea);
                turnDirection = (int) (Math.abs(contourAreas[0].doubleValue() - contourAreas[1].doubleValue())
                        / contourAreas[0].doubleValue() - contourAreas[1].doubleValue());
                break;

            }
        }
    }